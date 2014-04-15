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
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		
		if (stack.length <= 5)
			return "";
		
		StackTraceElement element = stack[5];
		
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
