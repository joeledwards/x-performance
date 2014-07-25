package structure;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Arrays
{
	public static void main(String[] args) throws Throwable
	{
		byte[] a = new byte[1];
		byte[] b = new byte[1];
		
		a[0] = ' ';
		b[0] = ' ';
		
		System.out.println(a.equals(b));
		System.out.println(java.util.Arrays.equals(a, b));
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String dA = DatatypeConverter.printBase64Binary(digest.digest(a));
		
		digest.reset();
		String dB = DatatypeConverter.printBase64Binary(digest.digest(b));
		
		
		System.out.println(dA.equals(dB));
		System.out.println("dA=" + dA);
		System.out.println("dB=" + dB);
	}
}
