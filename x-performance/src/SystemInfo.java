import java.net.InetAddress;

public class SystemInfo
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("LocalHost: " + InetAddress.getLocalHost());
		System.out.println("LoopbackHost: " + InetAddress.getLoopbackAddress());
	}
}
