package time;

public class NanoTime
{
	public static void main(String[] args)
	{
		long totalTime = 0;
		long startTime = System.nanoTime();
		
		while (totalTime < 2000000000)
		{
			long time = System.nanoTime();
			totalTime = time - startTime;
			System.out.println("time = " + time);
		}
	}
}
