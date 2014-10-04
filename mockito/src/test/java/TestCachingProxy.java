package test.java;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import main.java.CachingProxy;
import main.java.HeaderManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestCachingProxy {

	CachingProxy proxy = null;
	HeaderManager mockHeaderMgr = null;
	
	@Before
	public void setUp() {
		// Create the mocks
		mockHeaderMgr = mock(HeaderManager.class);
		proxy = new CachingProxy(mockHeaderMgr);
	}
	
	@Test
	public void testMax() {
		int max = proxy.max(1000, 200);
		assertEquals(200, max);
	}
	
	@Test
	public void testCurrentAgeIsZero() {
		// Setup expectations
		when(mockHeaderMgr.getAgeValue()).thenReturn(0);
		when(mockHeaderMgr.getDateValue()).thenReturn(0);
		when(mockHeaderMgr.getRequestTime()).thenReturn(0);
		when(mockHeaderMgr.getResponseTime()).thenReturn(0);
		when(mockHeaderMgr.getNow()).thenReturn(0);
		
		// Make the call
		int currentAge = proxy.calculateCurrentAge();
		
		// Assert a condition
		assertEquals(0, currentAge);
	}
	
	@Test
	public void testCurrentAgeIsOne() {
		// Setup expectations
		when(mockHeaderMgr.getAgeValue()).thenReturn(0);
		when(mockHeaderMgr.getDateValue()).thenReturn(0); 
		when(mockHeaderMgr.getRequestTime()).thenReturn(0);
		when(mockHeaderMgr.getResponseTime()).thenReturn(1);
		when(mockHeaderMgr.getNow()).thenReturn(0);
		
		// Make the call
		int currentAge = proxy.calculateCurrentAge();
		
		// Assert a condition
		assertEquals(1, currentAge);
	}
	
	@Test
	public void testCurrentAgeIsOneForGhost() {
		// Setup expectations
		when(mockHeaderMgr.getAgeValue()).thenReturn(0);
		when(mockHeaderMgr.getDateValue()).thenReturn(0);
		when(mockHeaderMgr.getRequestTime()).thenReturn(0);
		when(mockHeaderMgr.getResponseTime()).thenReturn(1);
		when(mockHeaderMgr.getNow()).thenReturn(0);
		
		// Make the call
		int currentAge = proxy.calculateCurrentAge(); //proxy.calculateCurrentAgeWithGhostObject();
		
		// Assert a condition
		assertEquals(1, currentAge);
	}

}
