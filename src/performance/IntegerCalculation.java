package performance;

public class IntegerCalculation extends PerformanceTest
{
	public void performTest()
	{
		long value = Math.round(Math.random()) * Math.round(Math.random()) * 4096;
		System.out.println("value = " + value);
	}
}
