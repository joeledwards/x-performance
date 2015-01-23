package collections;

import java.util.Arrays;
import java.util.List;

import util.PrintUtil;

public class ArgumentsAsList
{
	public static void main(String[] args)
	{
		PrintUtil.print("Nothing:");
		list().forEach(item -> PrintUtil.print("  " + item));;
		PrintUtil.print("Three Letters:");
		list("A", "B", "C").forEach(item -> PrintUtil.print("  " + item));;
	}
	
	private static <T> List<T> list(T... values)
	{
		return Arrays.asList(values);
	}
}
