package util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
	public static String formatTime(Calendar time)
	{
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %1$tz", time);
	}

	public static String formatTime(Date time)
	{
		return formatTime(time.getTime());
	}

	public static String formatTime(long time)
	{
		return formatTime(createCalendar(time));
	}
	
	public static String formatNanos(long nanos)
	{
		double milliseconds = nanos / 1000000.0;
		return String.format("%,f ms", milliseconds);
	}
	
	public static Calendar createCalendar(long time)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(time);
		
		return calendar;
	}
}
