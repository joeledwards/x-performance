import java.util.concurrent.LinkedBlockingQueue;

public abstract class ThreadTest<T> extends PerformanceTest
{
	private LinkedBlockingQueue<QueueItem<T>> inQueue;
	private LinkedBlockingQueue<QueueItem<T>> outQueue;
	private Thread testThread;
	
	public ThreadTest()
	{
		super();
		initialize();
	}
	
	public ThreadTest(String testName)
	{
		super(testName);
		initialize();
	}
	
	private void initialize()
	{
		inQueue = new LinkedBlockingQueue<QueueItem<T>>();
		outQueue = new LinkedBlockingQueue<QueueItem<T>>();
		testThread = new Thread(new ParallelThread<T>(inQueue, outQueue));
	}
	
	public void test(long iterations, long updateFrequency)
	{
		testThread.start();
		super.test(iterations, updateFrequency);
		
		try
		{
			inQueue.put(new QueueItem<T>(null));
		}
		catch (InterruptedException ex)
		{
			System.err.format("Interrupted while halting thread: %s\n", ex.toString());
		}
	}
	
	public void performTest()
	{
		try
		{
			T testItem = getTestItem();
			// System.err.format("[%s]> Adding item to queue\n", getClass().getName());
			inQueue.put(new QueueItem<T>(testItem));
			// System.err.format("[%s]> Waiting on queue input\n", getClass().getName());
			outQueue.take();
		}
		catch (InterruptedException ex)
		{
			System.err.format("Interrupted while communicating with thread: %s\n", ex.toString());
		}
	}
	
	public abstract T getTestItem();
}
