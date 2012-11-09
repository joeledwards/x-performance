
public class Main
{
	public static long TOTAL_ITERATIONS = 200 * 1024 * 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new LongThreadTest()).test(1024*1024, 8*1024);
		(new DoubleCalculation()).test(200*1024*1024, 1024*1024);
		(new IntegerCalculation()).test(200*1024*1024, 1024*1024);
	}
}
