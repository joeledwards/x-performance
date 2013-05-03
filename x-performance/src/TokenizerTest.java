import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Joel Edwards &lt;joeledwards@gmail.com&gt;
 * <br/>
 * <br/>
 * This class is a test platform for the {@link RoundRobinTokenizer}.
 * <br/>
 * <br/>
 * The test assembles a new string containing the characters of the
 * alphabet, in order, separated by spaces, and repeated 32 times.
 * <br/>
 * <br/>
 * The assembled string is passed to a chain of 26 {@link RoundRobinTokenizer}s,
 * one for every alphabetic character, which remove the first 'word' from
 * the string, then pass the string on to the remainder of the string to
 * the next {@link RoundRobinTokenizer} until the string is empty.
 */
public class TokenizerTest {
	private static final int THREAD_COUNT = 26;
	private static final int ITERATION_COUNT = 32;
	private static final String REGEX = "\\s+";

	public static void main(String[] args) throws Exception
	{
		String wordList = "";
		for (int i = 0; i < ITERATION_COUNT; i++)
			for (int j = 0; j < THREAD_COUNT; j++)
				wordList += (char) ('A' + j) + " ";
		wordList = wordList.trim();
		System.out.println("Original word list: " + wordList);

		ArrayList<StringBuilder> stringBuilders = new ArrayList<StringBuilder>();
		for (int i = 0; i < THREAD_COUNT; i++)
			stringBuilders.add(new StringBuilder());

		runTokenizers(wordList, REGEX, stringBuilders);
	
		System.out.println("Stream contents:");
		for (StringBuilder stringBuilder : stringBuilders)
			System.out.println("  " + stringBuilder.toString());
	}

	public static void runTokenizers(String words, String regex, Collection<StringBuilder> stringBuilders)
			throws InterruptedException
	{
		RoundRobinTokenizer lastTokenizer = null;
		RoundRobinTokenizer firstTokenizer = null;
		for (StringBuilder stringBuilder : stringBuilders)
		{
			RoundRobinTokenizer tokenizer = new RoundRobinTokenizer(stringBuilder, regex);
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
