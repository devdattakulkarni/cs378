import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestClassLoader extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
             HttpServletResponse response) 
             throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 
		 ClassLoader classLoader = TestClassLoader.class.getClassLoader();
		 out.println(classLoader.getClass().getName());
		 
		 ClassLoader parent = classLoader.getParent();
		 while(parent != null) {
			 out.println(parent.getClass().getName());
			 parent = parent.getParent();
		 }
	 }
}
