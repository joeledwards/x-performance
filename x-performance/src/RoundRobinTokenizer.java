import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public class RoundRobinTokenizer extends Thread
{
	public static void main(String[] args) throws Exception
	{
		int streamCount = 5;
		String regex = "\\s";
		String words = "Hi there Bob. How are you doing today. I believe you have my shoes!";
		ArrayList<ByteArrayOutputStream> streams = new ArrayList<ByteArrayOutputStream>();
		for (int i = 0; i < streamCount; i++)
			streams.add(new ByteArrayOutputStream());
		runTokenizers(words, regex, streams);

		System.out.println("Stream contents:");
		for (ByteArrayOutputStream stream : streams)
			System.out.println("  " + stream.toString());
	}

	public static void runTokenizers(String words, String regex, Collection<? extends OutputStream> streams)
			throws InterruptedException
	{
		RoundRobinTokenizer lastTokenizer = null;
		RoundRobinTokenizer firstTokenizer = null;
		for (OutputStream stream : streams)
		{
			RoundRobinTokenizer tokenizer = new RoundRobinTokenizer(stream, regex);
			if (lastTokenizer != null)
				lastTokenizer.setNextTokenizer(tokenizer);
			lastTokenizer = tokenizer;
			if (firstTokenizer == null)
				firstTokenizer = tokenizer;
			tokenizer.start();
		}
		lastTokenizer.setNextTokenizer(firstTokenizer);

		firstTokenizer.tokenize(words);

		firstTokenizer.join();
		RoundRobinTokenizer nextTokenizer = firstTokenizer.getNextTokenizer();
		while (nextTokenizer != firstTokenizer) {
			nextTokenizer.join();
			nextTokenizer = nextTokenizer.getNextTokenizer();
		}
	}


	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private OutputStream stream;
	private String regex;
	private RoundRobinTokenizer nextTokenizer;

	public RoundRobinTokenizer(OutputStream stream, String regex)
	{
		this.stream = stream;
		this.regex = regex;
	}

	public void setNextTokenizer(RoundRobinTokenizer nextTokenizer)
	{
		this.nextTokenizer = nextTokenizer;
	}

	public RoundRobinTokenizer getNextTokenizer() {
		return nextTokenizer;
	}

	public void tokenize(String words)
	{
		try {
			queue.put(words);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void log(String message) {
		System.out.println(getName() + ": " + message);
	}

	public void run()
	{
		String words = "INIT";
		while (words != null) {
			try {
				words = queue.take();
				String first = "";
				String rest = "";

				String[] parts = words.split(regex, 2);
				if (parts.length > 0)
					first = parts[0];
				if (parts.length > 1)
					rest = parts[1];

				nextTokenizer.tokenize(rest);

				if (first.length() > 0)
					stream.write(first.getBytes());

				if (rest.length() > 0)
					log(rest);
				else
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
