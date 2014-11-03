package com.guangjun.contactreminder;

import static com.guangjun.contactreminder.Constant.getNowDateString;
//import static com.bn.rcgl.Constant.getNowTimeString;
import static com.guangjun.contactreminder.DBUtil.getIdFromPrefs;
import android.util.Log;

public class Person 
{

	private int id;//每一个日程对应一个独一无二的sn码，在数据库中为主键
	private String name;
	private int level;//日程日期
	private int frequency;//日程时间
	private String phone;//闹钟日期
	private String email;//闹钟时间
	private String lastdate;//日程类型
	private String nextdate;//日程标题

	
	
	//创建新日程时的临时数据，只需要年月日三个数据，用来在刚刚进入新建日程界面日把年月日默认设置成当前日期
	public Person()
	{
		id=0;
		name="";
		lastdate="";
//		time1=toTimeString(8,0);//时间默认8点
		
		nextdate="";
//		time2=null;
//		
//		title="";
//		note="";
//		type="";
//		
//		timeSet=true;
//		alarmSet=false;
		
		phone="";
		email="";
		level=0;
		frequency=50;
				
	}
	
	//此构造器为从数据库读取日程对象时用
	public Person(int id,String name, int level, int frequency, String phone,String email,String lastdate,String nextdate)
	{
		this.id=id;
		this.name=name;
		this.level=level;
		this.frequency=frequency;
		this.phone=phone;
		this.email=email;
		this.lastdate=lastdate;
		this.nextdate=nextdate;
//		this.date1=date1;
//		this.time1=time1;
//		this.date2=date2;
//		this.time2=time2;
//		this.title=title;
//		this.note=note;
//		this.type=type;
//		this.timeSet=Boolean.parseBoolean(timeSet);
//		this.alarmSet=Boolean.parseBoolean(alarmSet);
	}
	
	public int getYear()//获得年
	{
		String[] date=lastdate.split("/");
		int tmp=Integer.valueOf(date[0]);
		return tmp;
	}
	
	public int getMonth()//获得月
	{
		String[] date=lastdate.split("/");
		int tmp=Integer.valueOf(date[1]);
		return tmp;
	}
	
	public int getDay()//获得日
	{
		String[] date=lastdate.split("/");
		int tmp=Integer.valueOf(date[2]);
		return tmp;
	}
	
//	public int getHour()//获得时
//	{
//		String[] time=time1.split(":");
//		int tmp=Integer.valueOf(time[0]);
//		return tmp;
//	}
//	
//	public int getMinute()//获得分
//	{
//		String[] time=time1.split(":");
//		int tmp=Integer.valueOf(time[1]);
//		return tmp;
//	}
	
	public int getNextYear()//获得闹钟的年
	{
		String[] date=nextdate.split("/");
		int tmp=Integer.valueOf(date[0]);
		return tmp;
	}
	
	public int getNextMonth()//获得闹钟月
	{
		String[] date=nextdate.split("/");
		int tmp=Integer.valueOf(date[1]);
		return tmp;
	}
	
	public int getNextDay()//获得闹钟日
	{
		String[] date=nextdate.split("/");
		int tmp=Integer.valueOf(date[2]);
		return tmp;
	}
	
//	public int getAHour()//获得闹钟时
//	{
//		String[] time=time2.split(":");
//		int tmp=Integer.valueOf(time[0]);
//		return tmp;
//	}
//	
//	public int getAMin()//获得闹钟分
//	{
//		String[] time=time2.split(":");
//		int tmp=Integer.valueOf(time[1]);
//		return tmp;
//	}
//	
//	public void setType(String s)//设置类型
//	{
//		this.type=s;
//	}
//	
//	public String getType()//获得类型
//	{
//		return type;
//	}
//	
//	public void setTitle(String s)//设置标题
//	{
//		this.title=s;
//	}
//	
//	public String getTitle()//获得标题
//	{
//		return title;
//	}
//	
//	public void setNote(String s)//设置备注
//	{
//		this.note=s;
//	}
//	
//	public String getNote()//获得备注
//	{
//		return note;
//	}
//	
//	public void setTimeSet(boolean b)//设置是否设置具体时间的布尔值
//	{
//		this.timeSet=b;
//		if(!timeSet)//如果为false说明没有设置具体时间，则具体时间默认为当天最后一分钟
//		{
//			time1="23:59";
//		}
//	}
//	
//	public boolean getTimeSet()//得到是否设了时间
//	{
//		return timeSet;
//	}
//	
//	public void setAlarmSet(boolean b)//设置是否设置闹钟的布尔值
//	{
//		this.alarmSet=b;
//		if(!timeSet)//如果为false说明没有设置闹钟，则闹钟置null
//		{
//			date2=null;
//			time2=null;
//		}
//	}
//	
//	public boolean getAlarmSet()//得到是否设置了闹钟
//	{
//		return alarmSet;
//	}
//	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getDate1()//得到日程日期
//	{
//		return date1;
//	}
//	
	
	public void setLastDate(String y,String m,String d)//设置日程日期，转换成YYYY/MM/DD
	{
		StringBuffer sb=new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m);
		sb.append("/");
		sb.append(d);
		lastdate=sb.toString();
	}
	
//	public void setTime1(String h,String m)//设置日程时间，转换成HH:MM
//	{
//		StringBuffer sb=new StringBuffer();
//		sb.append(h);
//		sb.append(":");
//		sb.append(m);
//		time1=sb.toString();
//	}
//	
//	public String getTime1()//获得日程时间
//	{
//		return time1;
//	}
//	
	public void setNextDate(String y,String m,String d)//设置闹钟日期
	{
		StringBuffer sb=new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m);
		sb.append("/");
		sb.append(d);
		nextdate=sb.toString();
	}
//	
//	public String getDate2()//得到闹钟日期
//	{
//		return date2;
//	}
//	
//	public void setTime2(String h,String m)//设置闹钟时间
//	{
//		StringBuffer sb=new StringBuffer();
//		sb.append(h);
//		sb.append(":");
//		sb.append(m);
//		time2=sb.toString();
//	}
//	
//	public String getTime2()//得到闹钟时间
//	{
//		return time2;
//	}	
//	
//	public void setSn(int sn)//设置sn码 
//	{
//		this.sn = sn;
//	}
//
//	public int getSn() //得到sn码
//	{
//		return sn;
//	}

	public static String toDateString(int y,int m,int d)//静态方法，把int型的年月日转换成YYYY/MM/DD
	{
		StringBuffer sb = new StringBuffer();
		sb.append(y);
		sb.append("/");
		sb.append(m<10?"0"+m:""+m);
		sb.append("/");
		sb.append(d<10?"0"+d:""+d);
		return sb.toString();
	}
	
	
	
//	public String toTimeString(int h,int m)//把int型的时分转换成HH:MM
//	{
//		StringBuffer sb = new StringBuffer();
//		sb.append(h<10?"0"+h:""+h);
//		sb.append(":");
//		sb.append(m<10?"0"+m:""+m);
//		return sb.toString();
//	}
//		
//	public String typeForListView()//用来得到在主界面的ListView里显示的类型格式
//	{
//		StringBuffer sbTmp=new StringBuffer();
//		sbTmp.append("[");
//		sbTmp.append(type);
//		sbTmp.append("]");
//		return sbTmp.toString();
//	}
//	
//	public String dateForListView()//用来得到在主界面的ListView里显示的日期格式
//	{
//		StringBuffer sbTmp=new StringBuffer();
//		sbTmp.append(date1);
//		sbTmp.append("   ");
//		return sbTmp.toString();
//	}
//	
//	public String timeForListView()//用来得到在主界面的ListView里显示的时间格式
//	{
//		if(!timeSet)
//		{
//			return "- -:- -   ";
//		}
//		StringBuffer sbTmp=new StringBuffer();
//		sbTmp.append(time1);
//		sbTmp.append("   ");
//		return sbTmp.toString();
//	}
//	
//	public boolean isPassed()//让日程设置时间与当前时间相比，判断日程是否已过期
//	{
//		String nowDate=getNowDateString();
//		String nowTime=getNowTimeString();
//		String schDate=date1;
//		String schTime=timeSet?time1:"23:59";//如果日程没有设置时间，则认为过了当天23:59，也就是到了第二天才过时
//							
//		if(nowDate.compareTo(schDate)>0||(nowDate.compareTo(schDate)==0&&nowTime.compareTo(schTime)>0))
//		{
//			return true;
//		}
//		return false;
//	}
	
	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getNextdate() {
		return nextdate;
	}

	public void setNextdate(String nextdate) {
		this.nextdate = nextdate;
	}

	public String toInsertSql(MainActivity father)//获取schedule对象存入数据库时的sql语句
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into person values(");
		id=getIdFromPrefs(father);
		sb.append(id);
		sb.append(",'");
		sb.append(name);
		sb.append("','");
		sb.append(level);
		sb.append("','");
		sb.append(frequency);
		sb.append("','");
		sb.append(phone);
		sb.append("','");
		sb.append(email);
		sb.append("','");
		sb.append(lastdate);
		sb.append("','");
		sb.append(nextdate);
		sb.append("')");	
		Log.d("toInsertSql",sb.toString());
		return sb.toString();
	}
	
//	public String toUpdateSql(MainActivity father)//获取schedule对象更新时的sql语句
//	{
//		int preSn=sn;//记录之前的sn
//		StringBuffer sb = new StringBuffer();
//		sb.append("update schedule set sn=");
//		sn=getSNFromPrefs(father);//换成新的sn
//		sb.append(sn);
//		sb.append(",date1='");
//		sb.append(date1);
//		sb.append("',time1='");
//		sb.append(time1);
//		sb.append("',date2='");
//		sb.append(date2);
//		sb.append("',time2='");
//		sb.append(time2);
//		sb.append("',title='");
//		sb.append(title);
//		sb.append("',note='");
//		sb.append(note);
//		sb.append("',type='");
//		sb.append(type);
//		sb.append("',timeset='");
//		sb.append(timeSet);
//		sb.append("',alarmset='");
//		sb.append(alarmSet);
//		sb.append("' where sn=");
//		sb.append(preSn);
//		Log.d("toUpdateSql",sb.toString());
//		return sb.toString();
//	}	
	
	public String toUpdateSql(MainActivity father)//获取schedule对象更新时的sql语句
	{
		int preId=id;//记录之前的sn
		StringBuffer sb = new StringBuffer();
		sb.append("update person set id=");
		id=getIdFromPrefs(father);//换成新的sn
		sb.append(id);
		sb.append(",name='");
		sb.append(name);
		sb.append("',level=");
		sb.append(level);
		sb.append(",frequency=");
		sb.append(frequency);
		sb.append(",phone='");
		sb.append(phone);
		sb.append("',email='");
		sb.append(email);
		sb.append("',lastdate='");
		sb.append(lastdate);
		sb.append("',nextdate='");
		sb.append(nextdate);
		sb.append("' where id=");
		sb.append(preId);
		Log.d("toUpdateSql",sb.toString());
		return sb.toString();
	}

}