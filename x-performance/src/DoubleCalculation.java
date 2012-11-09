public class DoubleCalculation
extends PerformanceTest
{
	public static long TOTAL_ITERATIONS = 1024 * 1024 * 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PerformanceTest test = new DoubleCalculation();
		test.test("DoubleCalculation", TOTAL_ITERATIONS);
	}
	
	public void performTest()
	{
		double value = Math.random() * Math.random();
	}
}
