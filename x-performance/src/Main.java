
public class Main
{
	public static long TOTAL_ITERATIONS = 200 * 1024 * 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Parent Final:    " + Parent.makeKeyFromFinal());
		System.out.println("Parent Variable: " + Parent.makeKeyFromVariable());
		System.out.println("Parent Method:   " + Parent.makeKeyFromMethod());
		System.out.println("Child Final:     " + Child.makeKeyFromFinal());
		System.out.println("Child Variable:  " + Child.makeKeyFromVariable());
		System.out.println("Child Method:    " + Child.makeKeyFromMethod());
		//(new LongThreadTest()).test(1024*1024, 8*1024);
		//(new DoubleCalculation()).test(200*1024*1024, 1024*1024);
		//(new IntegerCalculation()).test(200*1024*1024, 1024*1024);

		
	}
}
