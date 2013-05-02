import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

public class RoundRobinTokenizer extends Thread
{
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
