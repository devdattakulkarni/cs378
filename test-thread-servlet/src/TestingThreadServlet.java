import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

public class TestingThreadServlet extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int counter = 0;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		String userAgent = (String) request.getHeader("X-User-Agent");
		if (userAgent != null && userAgent.equalsIgnoreCase("TestRunner")) {
			increment();
		}
		
		Enumeration<String> headers = request.getHeaderNames();
		/*while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			String value = request.getHeader(header);
			response.getWriter().println("Header:" + header + " Value:" + value);					
		}*/
		
		response.getWriter().println("Testing thread says hello. Counter value is:" + counter);		
	}
	
	private synchronized void increment() {
		counter++;		
	}
}