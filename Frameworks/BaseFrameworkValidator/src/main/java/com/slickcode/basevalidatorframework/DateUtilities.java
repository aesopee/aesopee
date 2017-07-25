package com.slickcode.basevalidatorframework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.slickcode.baseframework.utils.StringUtilities;

public class DateUtilities {

	public static boolean isDate(String value, String format) {
		if (null == parseStringToDate(value, format)) {
			return false;
		} else {
			return true;
		}
	}

	public static Date parseStringToDate(String value, String format) {
		if(StringUtilities.isEmpty(value)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(value);
		} catch (ParseException e) {

		}
		return date;
	}
	
	public static String parseDateToString(Date value, String format) {
		if(null == value) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(value);
	}
}
