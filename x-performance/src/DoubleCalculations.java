import java.util.Calendar;


public class DoubleCalculations
extends PerformanceTest
{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PerformanceTest test = new DoubleCalculations();
		test.test();
		Calendar start = test.getStart();
		Calendar end = test.getEnd();
		System.out.format("Test began: %s\n", PerformanceTest.formatCalendar(start));
		System.out.format("Test ended: %s\n", PerformanceTest.formatCalendar(end));
		long msDiff = end.getTimeInMillis() - start.getTimeInMillis();
		System.out.format("Test duration was: %d.%d seconds\n", msDiff / 1000, msDiff % 1000);
	}
	
	public void performTest()
	{
		for (int i=0; i < 1024 * 1024 * 100; i++) {
			double value = Math.random() * Math.random();
		}
	}
}
