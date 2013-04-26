import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class RoundRobinTokenizer extends Thread
{
	public static void main(String[] args) throws Exception
	{
		int streamCount = 3;
		String words = "Hi there Bob. How are you doing today. I believe you have my shoes!";
		ArrayList<ByteArrayOutputStream> streams = new ArrayList<ByteArrayOutputStream>();
		for (int i = 0; i < streamCount; i++)
			streams.add(new ByteArrayOutputStream());
		runTokenizers(words, streams);

		for (ByteArrayOutputStream stream : streams)
			System.out.println(stream.toString());
	}

	public static void runTokenizers(String words, Collection<? extends OutputStream> streams)
			throws InterruptedException
	{
		RoundRobinTokenizer lastTokenizer = null;
		RoundRobinTokenizer firstTokenizer = null;
		for (OutputStream stream : streams)
		{
			RoundRobinTokenizer tokenizer = new RoundRobinTokenizer(stream);
			tokenizer.setNextTokenizer(lastTokenizer);
			lastTokenizer = tokenizer;
			if (firstTokenizer == null)
				firstTokenizer = tokenizer;
			tokenizer.start();
		}
		firstTokenizer.setNextTokenizer(firstTokenizer);

		firstTokenizer.tokenize(words);
		firstTokenizer.join();
	}

	private OutputStream stream;
	private RoundRobinTokenizer nextTokenizer;

	public RoundRobinTokenizer(OutputStream stream)
	{
		this.stream = stream;
	}

	public void setNextTokenizer(RoundRobinTokenizer nextTokenizer)
	{
		this.nextTokenizer = nextTokenizer;
	}

	public void run()
	{
		// TODO: complete
	}

	public void tokenize(String words)
	{
		// TODO: complete
	}
}
