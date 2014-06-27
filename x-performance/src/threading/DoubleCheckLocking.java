package threading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class DoubleCheckLocking
{
	private Greeting greeting;
	private Greeting grunting;
	
	/**
	 * This should be good :)
	 * 
	 * @return the greeting
	 */
	public Greeting getGreeting()
	{
		if(greeting == null)
			initGreeting();
		
		return greeting;
	}
	
	/**
	 * Initialize the greeting.
	 */
	public synchronized void initGreeting()
	{
		if(greeting == null)
			greeting = new Greeting("hi");
	}
	
	/**
	 * This could be bad :(
	 * 
	 * @return the grunt
	 */
	public Greeting getGrunting()
	{
		if(grunting == null)
		{
			synchronized (this)
			{
				if(grunting == null)
					grunting = new Greeting("grunt");
			}
		}
		
		return grunting;
	}
	
	public static void main(String[] args) throws Exception
	{
		final CountDownLatch latch = new CountDownLatch(1);
		final LinkedBlockingQueue<Greeting> greetings = new LinkedBlockingQueue<>();
		final LinkedBlockingQueue<Greeting> gruntings = new LinkedBlockingQueue<>();
		final DoubleCheckLocking tester = new DoubleCheckLocking();
		
		for(int i = 0; i < 100; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						latch.await();
						greetings.put(tester.getGreeting());
						gruntings.put(tester.getGrunting());
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		latch.countDown();
		
		Greeting first = null;
		
		for(int i = 0; i < 100; i++)
		{
			Greeting next = greetings.take();
			
			if(first == null)
				first = next;
			else if(first.getValue() != next.getValue())
				throw new Exception("Mismatch!");
			
			System.out.println(next.getValue());
		}
		
		first = null;
		
		for(int i = 0; i < 100; i++)
		{
			Greeting next = gruntings.take();
			
			if(first == null)
				first = next;
			else if(first.getValue() != next.getValue())
				throw new Exception("Mismatch!");
			
			System.out.println(next.getValue());
		}
	}
	
	private class Greeting
	{
		public String value;
		
		public Greeting(String value)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			this.value = new String(value);
		}
		
		public String getValue()
		{
			return value;
		}
	}
}
