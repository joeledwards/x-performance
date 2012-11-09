import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;


public class DoubleCalculations
extends PerformanceTest
{
	public static long TOTAL_ITERATIONS = 1024 * 1024 * 1024;
	public static long UPDATES_PER_ITERATION = 4 * 1024 * 1024;
	public static long ITERATIONS_PER_SPACE = 5;
	public static long SPACES_PER_NEWLINE = 5;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PerformanceTest test = new DoubleCalculations();
		Calendar now = GregorianCalendar.getInstance();
		System.out.format("Starting test [%s]\n", PerformanceTest.prettyTimestamp(now));
		test.test();
		Calendar start = test.getStart();
		Calendar end = test.getEnd();
		System.out.format("Test began @ %s\n", PerformanceTest.prettyTimestamp(start));
		System.out.format("Test ended @: %s\n", PerformanceTest.prettyTimestamp(end));
		long msDiff = end.getTimeInMillis() - start.getTimeInMillis();
		System.out.format("Test duration was: %d.%d seconds\n", msDiff / 1000, msDiff % 1000);
	}
	
	public void performTest()
	{
		NumberFormat prettyNum = DecimalFormat.getIntegerInstance();
						
		long nextInterval = UPDATES_PER_ITERATION;
		long nextSpace = ITERATIONS_PER_SPACE;
		long nextNewline = SPACES_PER_NEWLINE;
		
		for (long i=0; i < TOTAL_ITERATIONS; i++) {
			double value = Math.random() * Math.random();
			nextInterval--;
			if (nextInterval < 1) {
				System.err.format(".");
				nextInterval = UPDATES_PER_ITERATION;
				nextSpace--;
				if (nextSpace < 1) {
					System.err.format(" ");
					nextSpace = ITERATIONS_PER_SPACE;
					nextNewline--;
					if (nextNewline < 1) {
						String timeStr = PerformanceTest.prettyTime(GregorianCalendar.getInstance());
						String countStr = prettyNum.format(i);
						String totalStr = prettyNum.format(TOTAL_ITERATIONS);
						System.err.format("[%s]  %s / %s \n", timeStr, countStr, totalStr);
						nextNewline = SPACES_PER_NEWLINE;
					}
				}
			}
		}
	}
}
