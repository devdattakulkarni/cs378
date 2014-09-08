import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class TestingLoadOnStartup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.getWriter().println("Load on startup servlet responding.");		
	}
	

	@Override
	public void init() throws ServletException
	{
	    System.out.println("Servlet " + this.getServletName() + " has started.");
	    for (int i=0; i<5; i++) {
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    System.out.println("Servlet " + this.getServletName() + " initialization done.");	    
	}

	@Override
	public void destroy()
	{
	    System.out.println("Servlet " + this.getServletName() + " has stopped.");
	}
}