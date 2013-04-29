
public class Parent<T extends Parent<?>> {
	private static final String KEY_PREFIX = "PARENT-";
	protected static String keyPrefix = KEY_PREFIX;

	public static String makeKeyFromMethod() {
		return getKeyPrefix() + "PROTECTED-METHOD-KEY";
	}

	public static String makeKeyFromVariable() {
		
		return keyPrefix + "PROTECTED-VARIABLE-KEY";
	}

	public static String makeKeyFromFinal() {
		return KEY_PREFIX + "PRIVATE-FINAL-KEY";
	}

	protected static String getKeyPrefix() {
		return "PARENT-";
	}
}
