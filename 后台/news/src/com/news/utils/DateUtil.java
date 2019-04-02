package com.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 2017-03-27T13:11:50.657
	 * @param strDate
	 */
	public void gettodatetime(String strDate){

		strDate = strDate.replaceAll("T", " ");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Date dateTime;

		try {

		dateTime = formatter.parse(strDate);

		System.out.println(formatter.format(dateTime));

		} catch (ParseException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}
	}
}
