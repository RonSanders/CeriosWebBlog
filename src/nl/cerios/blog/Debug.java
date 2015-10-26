package nl.cerios.blog;

/**
 * This class sends messages to the console if the boolean debug is set to true
 * 
 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
 *
 */

public class Debug {
	static boolean debugMode = true;

	/**
	 * This is for debugging only and will have no use in the final product
	 * @param DebugMessage
	 */
	static void log(String DebugMessage){
		if(debugMode)
			System.out.println("Debug: " + DebugMessage);
	}
}
