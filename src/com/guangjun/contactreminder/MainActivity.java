package com.guangjun.contactreminder;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.guangjun.contactreminder.Person;
import com.guangjun.contactreminder.DateQuot;
import static com.guangjun.contactreminder.DBUtil.*;
import static com.guangjun.contactreminder.Constant.*;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {

	static ArrayList<Person> allperson = new ArrayList<Person>();
	Person tmpper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gotoMain();
	}

	public void gotoMain() {
		setContentView(R.layout.activity_main);

		final ArrayList<Boolean> alIsSelected = new ArrayList<Boolean>();// 记录ListView中哪项选中了的标志位

		ListView lv_main = (ListView) findViewById(R.id.lv_main);
		ImageButton ib_add = (ImageButton) findViewById(R.id.btn_add);

		allperson.clear();
		loadPerson(this);

		alIsSelected.clear();

		for (int i = 0; i < allperson.size(); i++)// 全部设置为false，即没有一项选中
		{
			alIsSelected.add(false);
		}

		lv_main.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(LinearLayout.VERTICAL);
				ll.setPadding(5, 5, 5, 5);

				TextView tvName = new TextView(MainActivity.this);
				tvName.setText(allperson.get(position).getName() + "   ");
				tvName.setTextSize(17);
				tvName.setTextColor(Color.parseColor("#129666"));
				ll.addView(tvName);

				String nowdate = Constant.getNowDateString();
				long days = DateQuot.getQuot(nowdate, allperson.get(position)
						.getLastdate());

				TextView tvDate = new TextView(MainActivity.this);
				tvDate.setText(days + "   ");
				tvDate.setTextSize(17);
				tvDate.setTextColor(Color.parseColor("#129666"));
				ll.addView(tvDate);

				// 如果该项被选中了，背景色设置为选中的背景色
				if (alIsSelected.get(position)) {
					ll.setBackgroundColor(Color.parseColor("#000000"));
				}

				return ll;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return allperson.size();
			}

			@Override
			public Object getItem(int position) {
				return allperson.get(position);
			}
		});
		lv_main.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// bCheck.setEnabled(true);//这三个按钮分别为主界面的日程查看、日程修改、日程删除,
				// bEdit.setEnabled(true);//如果用户在日程列表中选中了某个日程时，设为可用状态
				// bDel.setEnabled(true);

				tmpper = allperson.get(arg2);// 选中该项目时，把该项目对象赋给schTemp

				// 把标志位全部设为false后再把当前选中项的对应的标志位设为true
				for (int i = 0; i < alIsSelected.size(); i++) {
					alIsSelected.set(i, false);
				}
				alIsSelected.set(arg2, true);
				
				gotoPersonDetail();
			}
		});

		ib_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tmpper = new Person();
				gotoSetting();
			}
		});

	}

	public void gotoSetting() {
		setContentView(R.layout.add_person);
		// curr = Layout.SETTING;
		//
		// TextView tvTitle = (TextView) findViewById(R.id.tvnewscheduleTitle);
		// if (wcNewOrEdit == WhoCall.NEW) {
		// tvTitle.setText("新建日程");
		// } else if (wcNewOrEdit == WhoCall.EDIT) {
		// tvTitle.setText("修改日程");
		// }

		Button btSet = (Button) findViewById(R.id.btn_set);
		Button btCancle = (Button) findViewById(R.id.btn_cancel);
		final EditText etName = (EditText) findViewById(R.id.et_name);
		final EditText etPhone = (EditText) findViewById(R.id.et_phone);
		final EditText etEmail = (EditText) findViewById(R.id.et_email);
		final SeekBar barFrequency = (SeekBar) findViewById(R.id.bar_frequency);
		final RatingBar barLevel = (RatingBar) findViewById(R.id.bar_level);

		btCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gotoMain();
			}
		});

		btSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = etName.getText().toString().trim();
				String phone = etPhone.getText().toString().trim();
				String email = etEmail.getText().toString().trim();
				int level = barLevel.getProgress();
				int frequency = barFrequency.getProgress();

				if (name.equals("")) {
					Toast.makeText(MainActivity.this, "姓名不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}

				long days = 3 / frequency * 100;
				String lastdate = getNowDateString();
				tmpper.setName(name);
				tmpper.setLevel(level);
				tmpper.setFrequency(frequency);
				tmpper.setPhone(phone);
				tmpper.setEmail(email);
				tmpper.setLastdate(lastdate);
				tmpper.setNextdate(DateQuot.getQuotNextDate(lastdate, days));

				insertPerson(MainActivity.this);

				gotoMain();
			}
		});

	}

	public void gotoPersonDetail() {
		setContentView(R.layout.person_detail);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		tabHost.setup();

		tabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));

		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1_ind")
				.setContent(R.id.layout_setting));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2_ind")
				.setContent(R.id.tab2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// private class ViewHolder {
	// private ImageView imageView;
	// private TextView tvName, tvAuthor, tvPublisher, tvPrice;
	// }
}
