package performance;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelThread<T> implements Runnable
{
	LinkedBlockingQueue<QueueItem<T>> inThread;
	LinkedBlockingQueue<QueueItem<T>> outThread;
	
	public ParallelThread(LinkedBlockingQueue<QueueItem<T>> inThread, LinkedBlockingQueue<QueueItem<T>> outThread)
	{
		this.inThread = inThread;
		this.outThread = outThread;
	}
	
	@Override
	public void run()
	{
		try
		{
			boolean running = true;
			
			while (running)
			{
				// System.err.format("[%s:%d]> Waiting on queue input\n", getClass().getName(), count);
				QueueItem<T> item = inThread.take();
				// System.err.format("[%s:%d]> Returning item to queue\n", getClass().getName(), count);
				outThread.put(item);
				
				if (item.getItem() == null)
					running = false;
			}
		}
		catch (InterruptedException ex)
		{
			System.err.format("Interrupted: %s\n", ex.toString());
		}
	}
}
