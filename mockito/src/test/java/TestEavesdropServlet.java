package test.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import main.java.EavesdropHandler;
import main.java.EavesdropServlet;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestEavesdropServlet {

	EavesdropServlet eds = null;
	EavesdropHandler mockedHandler = null;
	
	@Before
	public void setUp() {
		// Create the mocks
		mockedHandler = mock(EavesdropHandler.class);
		eds = new EavesdropServlet(mockedHandler);
	}
	
	@Test
    public void thisAlwaysPasses() {
    }
	
	@Test
	public void testdoGet() throws Exception {
		// Step 1: Set the expectation
		String type = "";
		String project = "";
		String year = "";
		when(mockedHandler.getPageInformation(type, project, year)).thenReturn("correctPage");

		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		
		PrintWriter mockWriter = mock(PrintWriter.class);		
		when(mockRes.getWriter()).thenReturn(mockWriter);
		

		// Step 2: Make the call
		try {
			eds.doGet(mockReq, mockRes);
			assertTrue(true);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		verify(mockWriter).print("correctPage");
	}
	
	
	@Test(expected=ServletException.class)
	public void testdoGetPageNull() throws Exception {
		// Step 1: Set the expectation
		String type = "";
		String project = "";
		String year = "";
		
		String response = null;
		when(mockedHandler.getPageInformation(type, project, year)).thenReturn(response);

		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		HttpServletResponse mockRes = mock(HttpServletResponse.class);
		
		PrintWriter mockWriter = mock(PrintWriter.class);		
		when(mockRes.getWriter()).thenReturn(mockWriter);

		// Step 2: Make the call
		eds.doGet(mockReq, mockRes);
		verify(mockWriter, never()).print(response);
	}	
}