package classes;

public class NullInfo
{
	public static void main(String[] args)
	{
		Runnable runnable = new Runnable()
		{
			public void run()
			{
				; // Nothing
			}
		};
		
		System.out.println("runnable is " + runnable.getClass().getCanonicalName());
		System.out.println("runnable is " + runnable.getClass().getName());
	}
}
