import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

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
		String userAgent = (String) request.getHeader("User-Agent");
		if (userAgent != null && userAgent.equalsIgnoreCase("TestRunner")) {
			increment();
		}
		response.getWriter().println("Testing thread says hello. Counter value is:" + counter);		
	}
	
	private synchronized void increment() {
		counter++;		
	}
}