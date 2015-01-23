package time;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import util.PrintUtil;

public class TimestampTests
{
	//Timestamp ts = new Timestamp(System.currentTimeMillis());
	
	//2015-01-16 23:00:23
	public static void main(String[] args)
	{
		@SuppressWarnings("deprecation")
		Timestamp ts = new Timestamp(2015, 1, 16, 23, 0, 16, 0);
		GregorianCalendar cal = new GregorianCalendar(2015, 1, 16, 23, 0, 16);
		
		PrintUtil.print("  Timestamp time: " + ts.getTime());
		PrintUtil.print("     System time: " + System.currentTimeMillis());
		PrintUtil.print("   Calendar time: " + cal.getTimeInMillis());
	}
}
