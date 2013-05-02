import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;


public class TokenizerTest {

	public TokenizerTest() {
		// TODO Auto-generated constructor stub
	}

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

}
