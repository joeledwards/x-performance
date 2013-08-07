

public class StaticInstantiationTest
{
	public static void main(String[] args)
	{
		State a = State.A;
		State b = State.B;
		State c = State.C;

		System.out.println("A : " + a.getName());
		System.out.println("B : " + b.getName());
		System.out.println("C : " + c.getName());

		System.out.println("\"a\" : " + State.forName("a"));
		System.out.println("\"b\" : " + State.forName("b"));
		System.out.println("\"c\" : " + State.forName("c"));
	}

	public enum State
	{
		A("a"),
		B("b"),
		C("c");

		private static EnumProperty<State, String> nameProperty;

		private State(String name)
		{
			putName(this, name);
		}
		
		private static void putName(State constant, String name)
		{
			if (nameProperty == null)
				nameProperty = EnumProperty.newInstance();
			
			nameProperty.addPair(constant, name);
		}
		
		public String getName()
		{
			return nameProperty.getProperty(this);
		}
		
		public static State forName(String name)
		{
			return nameProperty.getConstant(name);
		}
	}
}
