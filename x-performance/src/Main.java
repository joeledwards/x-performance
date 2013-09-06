import java.net.URI;
import java.net.URL;

public class Main
{
	public static long TOTAL_ITERATIONS = 200 * 1024 * 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		System.out.println("Parent Final:    " + Parent.makeKeyFromFinal());
		System.out.println("Parent Variable: " + Parent.makeKeyFromVariable());
		System.out.println("Parent Method:   " + Parent.makeKeyFromMethod());
		System.out.println("Child Final:     " + Child.makeKeyFromFinal());
		System.out.println("Child Variable:  " + Child.makeKeyFromVariable());
		System.out.println("Child Method:    " + Child.makeKeyFromMethod());
		
		
//		String uriString = "https://joel:edwards@loadstorm.net/resource/query.py?q=help#answer";
		String uriString = "https://loadstorm.net";
		System.out.println("Original URL String: " + uriString);
		
		URI uri = new URI(uriString);
		System.out.println("URI toString:      " + uri.toString());
		System.out.println("URI toASCIIString: " + uri.toASCIIString());
		System.out.println("-------------------");
		System.out.println("  URI -    scheme: " + uri.getScheme());
		System.out.println("  URI - scheme-sp: " + uri.getSchemeSpecificPart());
		System.out.println("  URI - authority: " + uri.getAuthority());
		System.out.println("    URI ---- user-info: " + uri.getUserInfo());
		System.out.println("    URI --------- host: " + uri.getHost());
		System.out.println("    URI --------- port: " + uri.getPort());
		System.out.println("  URI --------- path: " + uri.getPath());
		System.out.println("  URI -------- query: " + uri.getQuery());
		System.out.println("  URI ----- fragment: " + uri.getFragment());
		
		URL url = new URL(uriString);
		System.out.println("URL toString():       " + url.toString());
		System.out.println("URL toExternalForm(): " + url.toExternalForm());
		System.out.println("-------------------");
		System.out.println("  URL -- protocol: " + url.getProtocol());
		System.out.println("  URL - authority: " + url.getAuthority());
		System.out.println("    URL ---- user-info: " + url.getUserInfo());
		System.out.println("    URL --------- host: " + url.getHost());
		System.out.println("    URL --------- port: " + url.getPort());
		System.out.println("   (URL - default-port: " + url.getDefaultPort() + ")");
		System.out.println("  URL ---- file: " + url.getFile());
		System.out.println("    URL --------- path: " + url.getPath());
		System.out.println("    URL -------- query: " + url.getQuery());
		
	}
}
