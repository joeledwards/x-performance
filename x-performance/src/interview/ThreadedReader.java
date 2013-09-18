package interview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// started @ 21:40
// logic completed @ 22:20
// main method completed @ 22:25
// files created @ 22:28
// debugging complete @ 22:40

public class ThreadedReader
{
	private static class ReadThread extends Thread
	{
		private BlockingQueue<String> consumer;
		private File source;
		private long linesRead;
		
		public ReadThread(BlockingQueue<String> consumer, File source)
		{
			this.consumer = consumer;
			this.source = source;
		}
		
		public void run()
		{
			BufferedReader reader = null;
			
			try
			{
				reader = new BufferedReader(new FileReader(source));
				
				String line = null;
				
				while ((line = reader.readLine()) != null)
				{
					consumer.put(line + "\n");
					linesRead++;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		public long getLinesRead()
		{
			return linesRead;
		}
	}
	
	private static class WriteThread extends Thread
	{
		private boolean halted = false;
		private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		private File destination = null;
		private long linesWritten = 0;
		
		public WriteThread(File destination)
		{
			this.destination = destination;
		}
		
		public LinkedBlockingQueue<String> getQueue()
		{
			return queue;
		}
		
		public void run()
		{
			BufferedWriter writer = null;
			
			try
			{
				writer = new BufferedWriter(new FileWriter(destination));
				
				while (queue.size() > 0 || !halted)
				{
					String line = queue.take();
					
					if (line != null && line.length() > 0)
					{
						writer.write(line);
						linesWritten++;
					}
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					writer.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		public void halt()
		{
			halted = true;
			queue.offer("");
		}
		
		public long getLinesWritten()
		{
			return linesWritten;
		}
	}
	
	public static void main(String args[])
	{
		String home = System.getProperty("user.home");
		
		File destination = new File(home + "/Desktop/destination.csv");
		List<File> sources = new LinkedList<File>();
		
		for (int i = 1; i <= 5; i++)
		{
			sources.add(new File(home + "/Desktop/source-" + i + ".csv"));
			System.out.println(sources.get(i-1).getAbsolutePath());
		}
		
		readFiles(sources, destination);
	}
	
	private static void readFiles(List<File> sources, File destination)
	{
		WriteThread writeThread = new WriteThread(destination);
		writeThread.start();
		
		List<ReadThread> readThreads = new LinkedList<ReadThread>();
		
		for (File source : sources)
		{
			ReadThread readThread = new ReadThread(writeThread.getQueue(), source);
			readThread.start();
			readThreads.add(readThread);
		}
		
		long linesRead = 0;
		
		for (ReadThread readThread : readThreads)
		{
			try
			{
				readThread.join();
				linesRead += readThread.getLinesRead();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			writeThread.halt();
			writeThread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		assert linesRead == writeThread.getLinesWritten();
		
		System.out.println("Wrote " + writeThread.getLinesWritten() + " of " + linesRead + " lines");
	}
}
