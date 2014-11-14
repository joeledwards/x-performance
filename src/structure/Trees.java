package structure;

import java.util.TreeSet;

import util.PrintUtil;

public class Trees
{
	public static void main(String[] args)
	{
		TreeSet<Integer> tree = new TreeSet<>();
		
		tree.add(2);
		tree.add(8);
		tree.add(12);
		tree.add(16);
		
		for (int i = 0; i < 20; i++)
			PrintUtil.print("tree.ceiling(" + i + ")=" + tree.ceiling(i));
		
		for (int i = 0; i < 20; i++)
			PrintUtil.print("tree.floor(" + i + ")=" + tree.floor(i));
	}
}
