package main.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.EavesdropHandler;

public class EavesdropServlet extends HttpServlet {
	
	public EavesdropHandler eavesdropHandler = null;
	
	public EavesdropServlet() {
		super();
	}
	
	public EavesdropServlet(EavesdropHandler edh) {
		this();
		if (edh != null) {
			this.eavesdropHandler = edh;
		}
		else {
			this.eavesdropHandler = new EavesdropHandler();
		}
	}
	
	public void setEavesdropHandler(EavesdropHandler edh) {
		this.eavesdropHandler = edh;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Parse query parameters from request
		String type = ""; 
		String project = ""; 
		String year = "";
		
		String pageInfo = this.eavesdropHandler.getPageInformation(type, project, year);
		
		if (pageInfo == null) {
			throw new ServletException();
		}
		
		response.getWriter().print(pageInfo);		
	}

}
