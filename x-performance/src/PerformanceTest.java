import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public abstract class PerformanceTest
implements Test
{
	public static long UPDATES_PER_ITERATION = 4 * 1024 * 1024;
	public static long UPDATES_PER_SPACE = 5;
	public static long SPACES_PER_NEWLINE = 5;
	
	private Calendar start;
	private Calendar end;
	
	private String testName;
	
	public PerformanceTest()
	{
		testName = this.getClass().getName();
	}
	
	public PerformanceTest(String testName)
	{
		this.testName = testName;
	}
	
	public void test(long iterations, long updateFrequency)
	{	
		start = GregorianCalendar.getInstance();
		System.out.format("Test Name \"%s\"\n", testName);
		System.out.format("Test began @ %s\n", PerformanceTest.prettyTimestamp(start));

		NumberFormat prettyNum = DecimalFormat.getIntegerInstance();
		long nextInterval = updateFrequency;
		long nextSpace = UPDATES_PER_SPACE;
		long nextNewline = SPACES_PER_NEWLINE;
		
		for (long i=0; i < iterations; i++) {
			performTest();
			nextInterval--;
			if (nextInterval < 1) {
				System.err.format(".");
				nextInterval = updateFrequency;
				nextSpace--;
				if (nextSpace < 1) {
					System.err.format(" ");
					nextSpace = UPDATES_PER_SPACE;
					nextNewline--;
					if (nextNewline < 1) {
						String timeStr = PerformanceTest.prettyTime(GregorianCalendar.getInstance());
						String countStr = prettyNum.format(i);
						String totalStr = prettyNum.format(iterations);
						System.out.format("[%s]  %s / %s \n", timeStr, countStr, totalStr);
						nextNewline = SPACES_PER_NEWLINE;
					}
				}
			}
		}
		
		end = GregorianCalendar.getInstance();
		System.out.format("\n");
		System.out.format("Test ended @: %s\n", PerformanceTest.prettyTimestamp(end));
		long msDiff = end.getTimeInMillis() - start.getTimeInMillis();
		System.out.format("Test duration was: %d.%d seconds\n", msDiff / 1000, msDiff % 1000);
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
