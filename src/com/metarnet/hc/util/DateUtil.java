/**
 * 
 */
package com.metarnet.hc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuhy
 */
public class DateUtil {

	/**
	 * 
	 * @param alarmnetime yyyy-MM-dd hh:mm:ss
	 * @param fLICKPERIOD
	 * @return
	 */
	public static String getOffSetDate(String alarmnetime, int fLICKPERIOD) {
		String ret = alarmnetime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date d = sdf.parse(alarmnetime);
			d.setTime(d.getTime() - fLICKPERIOD * 60 * 60 * 1000);
			ret = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String getOffSetDate(Date d, int fLICKPERIOD) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		d.setTime(d.getTime() - fLICKPERIOD * 60 * 60 * 1000);
		String ret = sdf.format(d);
		return ret;
	}
	
	public static String formatDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(Long.valueOf(time));
		String ret = sdf.format(d);
		return ret;
	}

}
