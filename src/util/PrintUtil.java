package util;

public class PrintUtil
{
	public static void print(String string)
	{
		System.out.println(string);
	}
	
	public static void format(String format, Object ... args)
	{
		System.out.format(format + "\n", args);
	}
	
	public static void printTime(String string)
	{
		System.out.println(timeInfo() + " : " + string);
	}
	
	public static void formatTime(String format, Object ... args)
	{
		System.out.format(timeInfo() + " : " + format + "\n", args);
	}
	
	public static void printCaller(String string)
	{
		System.out.println(callerInfo() + " : " + string);
	}
	
	public static void formatCaller(String format, Object ... args)
	{
		System.out.format(callerInfo() + " : " + format + "\n", args);
	}
	
	public static void printThread(String string)
	{
		System.out.println(threadInfo() + " : " + string);
	}
	
	public static void formatThread(String format, Object ... args)
	{
		System.out.format(threadInfo() + " : " + format + "\n", args);
	}
	
	private static String getCaller()
	{
		String selfName = PrintUtil.class.getSimpleName();
		boolean foundSelf = false;
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement element = null;
		
		for (int i = 0; i < stack.length; i++)
		{
			String fullClass = stack[i].getClassName();
			int nameOffset = fullClass.lastIndexOf('.') + 1;
			String className = (nameOffset > 0) ? fullClass.substring(nameOffset, fullClass.length()) : "";
			
			if (foundSelf)
			{
				if (!selfName.equals(className))
				{
					element = stack[i];
					break;
				}
			}
			else
			{
				if (selfName.equals(className))
					foundSelf = true;
			}
		}
		
		if (element == null)
			element = stack[stack.length - 1];
		
		return String.format("%s.%s", element.getClassName(), element.getMethodName());
	}
	
	private static String timeInfo()
	{
		return DateUtil.formatTime(System.currentTimeMillis());
	}
	
	private static String callerInfo()
	{
		return "[Caller :: " + getCaller() + "] " + timeInfo();
	}

	private static String threadInfo()
	{
		return "[Thread :: " + Thread.currentThread().getName() + "] " + callerInfo();
	}
	
	public static void main(String[] args)
	{
		formatThread("My name is: %s", PrintUtil.class.getSimpleName());
	}
}
