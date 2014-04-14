package time;

public class NanoTime
{
	public static void main(String[] args)
	{
		long nanoTotal = 0;
		long nanoStart = System.nanoTime();
		
		long msTotal = 0;
		long msStart = System.currentTimeMillis();
		
		while (nanoTotal < 2000000000)
		{
			long nanoTime = System.nanoTime();
			long msTime = System.currentTimeMillis();
			
			nanoTotal = nanoTime - nanoStart;
			msTotal = msTime - msStart;
			
			System.out.println("nano   time = " + nanoTime);
			System.out.println("millis time = " + msTime);
		}
		
		System.out.println("total millis elapsed = " + msTotal);
	}
}
