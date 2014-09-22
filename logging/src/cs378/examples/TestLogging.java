package cs378.examples;

import java.util.logging.*;

public class TestLogging {	
	public static void main(String [] args) {
		Logger infoLogger = Logger.getLogger("cs378.examples.TestLogging");
		
		//Logger parentLogger = Logger.getLogger("cs378.examples");
		//parentLogger.setLevel(Level.CONFIG);
		
		//Logger parentLogger1 = Logger.getLogger("cs378");
		//parentLogger1.setLevel(Level.SEVERE);		
		
		Handler[] parentHandlers = infoLogger.getParent().getHandlers();
		for(int i=0; i<parentHandlers.length; i++) {
			System.out.println("Handler level:" + parentHandlers[i].getLevel());
		}
		System.out.println("Parent level:" + infoLogger.getParent().getLevel());
		
		// Uncomment below -- test case 2
		//infoLogger.setLevel(Level.FINEST);
		//infoLogger.getParent().setLevel(Level.FINEST);		
		//infoLogger.getParent().getHandlers()[0].setLevel(Level.FINEST);;
				
		//Handler consoleHandler = new ConsoleHandler();
		//consoleHandler.setLevel(Level.FINEST);
		//infoLogger.addHandler(consoleHandler);
		
		//infoLogger.setParent(parentLogger);
		
		infoLogger.severe("Logging a severe message.");
		infoLogger.warning("Logging a warning message.");
		infoLogger.info("Logging an info message.");
		infoLogger.config("Logging a config message.");
		infoLogger.fine("Logging a fine message.");
		infoLogger.finer("Logging a finer message.");
		infoLogger.finest("Logging a finest message.");
		
		
	}
}
