package streams;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class SimpleStream
{
	public static void main(String[] args)
	{
		TreeSet<Integer> integers = new TreeSet<>();
		integers.add(7);
		integers.add(9);
		integers.add(11);
		integers.add(1);
		integers.add(6);
		integers.add(2);
		integers.add(1);
		integers.add(6);
		integers.add(13);
		
		System.out.println("Integers (original): " + integers);
		
		System.out.println("Integers (prefixed): " + 
				String.join(", ",
						integers.stream()
						.map(i -> "Item-" + String.valueOf(i))
								.collect(Collectors.toSet())
				)
		);
	}
}
