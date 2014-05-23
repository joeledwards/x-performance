package conversion;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTests
{
	public static void main(String[] args)
	{
		String funnyName = "Вадим Якубец";
		System.out.println(funnyName);
		
		funnyName = "Нил Шарафутдинов";
		System.out.println(funnyName);
		
		System.out.println("Available Charsets:");
		
		for (SortedMap.Entry<String, Charset> entry : Charset.availableCharsets().entrySet())
			System.out.println("  " + entry.getKey() + " : " + entry.getValue());
	}
}
