package conversion;

public class Formatting
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		double d =  12345.6789;
		System.out.format("double (normal) : %f\n", d);
		System.out.format("double (%%.2f)   : %.2f\n", d);
		
		// 
		System.out.format("Seconds since epoch: %.3f\n", 2 / 1000.0);
		
		// Crazy!
		System.out.format("%f\n", true ? new Integer(1) : new Double(2));
		System.out.println(true ? new Integer(1) : new Double(2));
	}
}
