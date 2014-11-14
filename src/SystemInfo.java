import java.net.InetAddress;

public class SystemInfo
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("localhost: " + InetAddress.getLocalHost());
		System.out.println("loopback address: " + InetAddress.getLoopbackAddress());
		System.out.println("temp directory: " + System.getenv("java.io.tmpdir"));
	}
}
