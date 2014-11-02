package com.guangjun.contactreminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class DateQuot {
	public static String getDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Date dd = new Date();
		return ft.format(dd);
	}

	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();			
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static String getQuotNextDate(String lastdate, long days){
		String nextdate="";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		try {
			long lastdateInMillis = ft.parse(lastdate).getTime();
			calendar.setTimeInMillis(lastdateInMillis+days*24*3600*1000);
			nextdate = ft.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextdate;
	}
	public static void main(String[] args) throws Exception {
		String date1 = "2015/8/8";
		String date2 = getDate();
		long day = getQuot(date1, date2);
		System.out.println("距离 " + date1 + " 还有 " + day + " 天");
		String nextDay = getQuotNextDate(date1, 5);
		System.out.println("5天后是："+nextDay);
	}
}