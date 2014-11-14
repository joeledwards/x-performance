package time;

public class NanoTime
{
	public static void main(String[] args)
	{
		long nanoTotal = 0;
		long nanoStart = System.nanoTime();
		
		long minNanos = Long.MAX_VALUE;
		long maxNanos = Long.MIN_VALUE;
		long msTotal = 0;
		long msStart = System.currentTimeMillis();
		long iterations = 0;
		long lastNanoTime = 0;
		
		while (nanoTotal < 2000000000)
		{
			long nanoTime = System.nanoTime();
			long msTime = System.currentTimeMillis();
			
			nanoTotal = nanoTime - nanoStart;
			msTotal = msTime - msStart;
			
			if (lastNanoTime > 0)
			{
				long nanoDiff = nanoTime - lastNanoTime;
				
				if(minNanos > nanoDiff)
					minNanos = nanoDiff;
				
				if(maxNanos < nanoDiff)
					maxNanos = nanoDiff;
			}
			
			lastNanoTime = nanoTime;
			
			iterations++;
		}
		
		System.out.println("total millis elapsed = " + msTotal + " (" + iterations + " iterations)");
		System.out.println("  Min nanos elapsed: " + minNanos);
		System.out.println("  Max nanos elapsed: " + maxNanos);
		
		System.out.format("System.nanoTime() precision: %,.9f\n", ((double)System.nanoTime() / 1_000_000_000.0));
		System.out.println("Between two System.nanoTime() calls: " + -1 * (System.nanoTime() - System.nanoTime()) + " ns");
	}
}
