package structure;

public class NullEnumInSwitch
{
	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		Option option = null;
		
		switch(option)
		{
			case OPTION_A:
				break;
			case OPTION_B:
				break;
			case OPTION_C:
				break;
			default:
				System.out.println("Supports null enum constant in switch!");
		}
	}
	
	public enum Option
	{
		OPTION_A,
		OPTION_B,
		OPTION_C
	}
}
