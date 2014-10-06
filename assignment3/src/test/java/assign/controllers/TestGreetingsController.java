package assign.controllers;

import static org.junit.Assert.assertEquals;
import assign.controllers.GreetingsController;

import org.junit.Before;
import org.junit.Test;

public class TestGreetingsController {
	
	public GreetingsController gs;
	
	@Before
	public void setUp() {
		gs = new GreetingsController();
	}
	
	@Test
	public void testGetGreetingsWithUserNamePassedIn() {
		String userName = "test user";
		String greeting = gs.getGreeting(userName);		
		assertEquals("Hi, test user", greeting);
	}
	
	/*@Test
	public void testGetGreetingsWithNoUserName() {
		assertEquals("Hello world", gs.getGreeting());
	}
	*/
}
