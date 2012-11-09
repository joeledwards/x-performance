import java.util.Calendar;
import java.util.GregorianCalendar;


public abstract class PerformanceTest
implements Test
{
	private Calendar start;
	private Calendar end;
	
	public PerformanceTest()
	{
	}
	
	public final void test()
	{
		start = GregorianCalendar.getInstance();
		performTest();
		end = GregorianCalendar.getInstance();
	}
	
	public abstract void performTest();
	
	public Calendar getStart()
	{
		return start;
	}
	
	public Calendar getEnd()
	{
		return end;
	}
	
	public static String prettyDate(Calendar c)
	{
		return String.format(	"%04d-%02d-%02d",
								c.get(Calendar.YEAR),
								c.get(Calendar.MONTH),
								c.get(Calendar.DAY_OF_MONTH));
	}
	
	public static String prettyTime(Calendar c)
	{
		return String.format(	"%02d:%02d:%02d.%03d",
								c.get(Calendar.HOUR),
								c.get(Calendar.MINUTE),
								c.get(Calendar.SECOND),
								c.get(Calendar.MILLISECOND));
	}
	
	public static String prettyTimestamp(Calendar c)
	{
		return String.format("%s %s", prettyDate(c), prettyTime(c));
	}
}
