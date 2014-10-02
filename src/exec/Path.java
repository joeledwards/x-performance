package exec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Path
{
	public static void main(String[] args) throws Exception
	{
		boolean running = true;
		Scanner stdin = new Scanner(System.in);
		
		while(running)
		{
			System.out.print("find executable path> ");
			System.out.flush();
			String executableName = stdin.nextLine();
			String path = findExecutablePath(executableName);
			System.out.println(executableName + ": " + ((path == null) ? "Not Found" : path));
		}
		
		stdin.close();
	}
	
	public static String findExecutablePath(String executableName) throws IOException, InterruptedException
	{
		final Process process = Runtime.getRuntime().exec("which " + executableName);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					InputStream stdout = process.getInputStream();
					
					byte[] buffer = new byte[4096];
					int bytesRead = 0;
					
					while((bytesRead = stdout.read(buffer)) != -1)
						os.write(buffer, 0, bytesRead);
					
					os.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		thread.join();
		
		if (process.exitValue() != 0)
			return null;
		
		String executablePath = os.toString(Charset.defaultCharset().name()).trim();
		
		if (executablePath.isEmpty())
			return null;
		
		return executablePath;
	}
}
