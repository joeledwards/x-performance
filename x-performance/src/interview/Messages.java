package interview;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Messages
{
	public static void main(String args[]) throws Exception
	{
		LinkedList<ConsumerThread> threads = new LinkedList<>();
		
		LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
		
		threads.add(new ConsumerThread(280, messageQueue));
		threads.add(new ConsumerThread(280, messageQueue));
		threads.add(new ConsumerThread(320, messageQueue));
		threads.add(new ConsumerThread(320, messageQueue));
		
		GeneratorThread generator = new GeneratorThread(10000, messageQueue);
		generator.start();
		
		Thread.sleep(2000);
		
		for (ConsumerThread thread : threads)
		{
			thread.start();
		}
		
		generator.join();
		System.out.println("Generator is done.");
		
		for (int i = 0; i < threads.size(); i++)
		{
			try
			{
				messageQueue.put("DONE");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		for (ConsumerThread thread : threads)
			thread.join();
		
	}
	
	public static class GeneratorThread extends Thread
	{
		private BlockingQueue<String> messageQueue;
		private long messageCount;
		private long generatedCount = 0;
		
		public GeneratorThread(long messageCount, BlockingQueue<String> messageQueue)
		{
			this.messageCount = messageCount;
			this.messageQueue = messageQueue;
		}
		
		public void run()
		{
			long start = System.nanoTime();
			long lastReport = System.nanoTime();
			
			while (--messageCount >= 0)
			{
				try
				{
					long workStart = System.nanoTime();
					
					messageQueue.put("message-" + generatedCount);
					generatedCount++;
					
					long now = System.nanoTime();
					
					if (now - lastReport > 1000000000)
					{
						System.out.println("ConsumerThread has generated " + generatedCount + " messages."
								+ " " + messageCount + " messages remaining.");
						lastReport = now;
					}
					
					long workEnd = System.nanoTime();
					
					long workDuration = workEnd - workStart;
					int sleepNanos = Math.max(0, Math.min(999999, 1000000 - (int) workDuration));
					
					long sleepStart = System.nanoTime();
					sleep(0, sleepNanos);
					long sleepEnd = System.nanoTime();
					
					long sleepDuration = sleepEnd - sleepStart;
					
					if (sleepDuration > (sleepNanos + 200000))
						System.out.println("Slept " + formatNanos(sleepDuration) + " (expected to sleep "
								+ formatNanos(sleepNanos) + ")."
								+ " Difference of " + formatNanos(sleepDuration - sleepNanos));
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			long end = System.nanoTime();
			
			System.out.println("ConsumerThread generated " + generatedCount + " messages."
					+ " There are " + messageQueue.size() + " left in the queue."
					+ " Took " + formatNanos((end - start) / 1000000));
		}
	}
	
	public static String formatNanos(double nanos)
	{
		return String.format("%,.3f ms", nanos / 1000000.0);
	}
	
	public static class ConsumerThread extends Thread
	{
		private long maxSleepNanos;
		private BlockingQueue<String> messageQueue;
		
		public ConsumerThread(long messagesPerSecond, BlockingQueue<String> messageQueue)
		{
			maxSleepNanos = 1000000000 / messagesPerSecond;
			this.messageQueue = messageQueue;
		}
		
		public void run()
		{
			String message = null;
			
			while (!"DONE".equals(message))
			{
				try
				{
					long workStart = System.nanoTime();
					message = messageQueue.take();
					long workEnd = System.nanoTime();
					
					long workDuration = workEnd - workStart;
					int adjustedSleep = Math.max(0, (int) (maxSleepNanos - workDuration));
					sleep(adjustedSleep / 1000000, adjustedSleep % 1000000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			System.out.println("ConsumerThread " + getName() + " done.");
		}
	}
}
