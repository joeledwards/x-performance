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
		
		for (ConsumerThread thread : threads)
		{
			thread.start();
		}
		
		generator.join();
		System.out.println("Generator is done.");
		
		for (ConsumerThread thread : threads)
		{
			try
			{
				messageQueue.put("DONE");
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
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
		
		public GeneratorThread(long messageCount, BlockingQueue messageQueue)
		{
			this.messageCount = messageCount;
			this.messageQueue = messageQueue;
		}
		
		public void run()
		{
			while (--messageCount >= 0)
			{
				try
				{
					System.out.println(messageCount + " messages left");
					messageQueue.put("message-" + generatedCount);
					generatedCount++;
					sleep(1L);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			System.out.println("ConsumerThread generated " + generatedCount + " messages");
		}
	}
	
	public static class ConsumerThread extends Thread
	{
		private long timerPerMessage;
		private BlockingQueue<String> messageQueue;
		
		public ConsumerThread(long messagesPerSecond, BlockingQueue<String> messageQueue)
		{
			timerPerMessage = 1000 / messagesPerSecond;
			this.messageQueue = messageQueue;
		}
		
		public void run()
		{
			String message = null;
			
			while (!"DONE".equals(message))
			{
				try
				{
					message = messageQueue.take();
					System.out.println("ConsumerThread " + getName() + " got message " + message);
					sleep(timerPerMessage);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("ConsumerThread " + getName() + " done.");
		}
	}
}
