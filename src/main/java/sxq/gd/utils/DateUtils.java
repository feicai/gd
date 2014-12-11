package sxq.gd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String TIME_PATTERN ="yyyy-MM-dd HH:mm:ss";
	
	public static String getCurrentTime(){
		SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
		return format.format(new Date());
	}
}
