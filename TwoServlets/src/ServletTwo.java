import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTwo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		String dblocation = this.getServletContext().getInitParameter("database");
		response.getWriter().println("Servlet 2: Database:" + dblocation);
		
		String password = this.getServletConfig().getInitParameter("password");		
		response.getWriter().println("Servlet 2: Password:" + password);
	}
}