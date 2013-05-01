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
		int streamCount = 26;
		int loopCount = 32;
		String regex = "\\s";
		String words = "";
		for (int i = 0; i < loopCount; i++) {
			for (int j = 0; j < streamCount; j++) {
				int letter = 'A' + j;
				words += (char) letter + " ";
			}
		}
		words = words.trim();

		System.out.println("Words: " + words);
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

	public void run()
	{
		boolean more = true;
		while (more) {
			try {
				String words = queue.take();

				String[] parts = words.split(regex, 2);
				String first = (parts.length > 0) ? parts[0] : "";
				String rest = (parts.length > 1) ? parts[1] : "";

				nextTokenizer.tokenize(rest);

				stream.write(first.getBytes());
				if (rest.length() == 0)
					more = false;
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
