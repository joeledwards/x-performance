package structure;


public class Child extends Parent<Parent<?>>
{
	private static final String KEY_PREFIX = "CHILD-";
	protected static String keyPrefix = KEY_PREFIX;

	protected static String getKeyPrefix()
	{
		return "CHILD-";
	}
}
