package structure;

import java.util.Collection;
import java.util.LinkedList;

public class Invariance
{
	public static void main(String[] args)
	{
		LinkedList<BlahBlah> blahblahs = new LinkedList<>();
		pain(blahblahs);
	}
	
	public static void pain(Collection<? extends Blah> blahs)
	{
		
	}
	
	public interface Blah
	{
		
	}
	
	public class BlahBlah implements Blah
	{
		
	}
}
