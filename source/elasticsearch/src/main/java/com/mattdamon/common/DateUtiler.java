package com.mattdamon.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtiler {

	public static String formatDate(Object object) {
		if (object instanceof java.util.Date) {
			return formatDateFromDate((java.util.Date) object);
		}
		return formatDateFromString(object.toString());
	}

	public static boolean isDate(Object object) {
		return object instanceof java.util.Date
				|| Pattern.matches("[1-2][0-9][0-9][0-9]-[0-9][0-9].*",
						object.toString());
	}

	public static String formatDateFromDate(Date date) {
		SimpleDateFormat dateFormat_hms = new SimpleDateFormat(
				Global.DATE_FORMAT_YMDHMS);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Global.DATE_FORMAT_YMD);
		try {
			String result = dateFormat_hms.format(date);
			return result;
		} catch (Exception e) {
			// ignore
		}
		try {
			String result = dateFormat.format(date) + "00:00:00";
			return result;
		} catch (Exception e) {
			// ignore
		}
		return dateFormat_hms.format(new Date());
	}

	public static String formatDateFromString(String date) {
		SimpleDateFormat dateFormat_hms = new SimpleDateFormat(
				Global.DATE_FORMAT_YMDHMS);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Global.DATE_FORMAT_YMD);
		try {
			Date value = dateFormat_hms.parse(date);
			return formatDateFromDate(value);
		} catch (Exception e) {
			// ignore
		}
		try {
			Date value = dateFormat.parse(date);
			return formatDateFromDate(value);
		} catch (Exception e) {
			// ignore
		}
		return dateFormat_hms.format(new Date());
	}
}
