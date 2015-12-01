package nl.cerios.blog;

/**
 * <h1>UserInterfaceManager</h1>
 * <b>This is a collection of all the user interface screens</b><br>
 * <ul>
 * <li>mainMenu</li>
 * <li>blogMenu</li>
 * <li>guestMenu</li>
 * </ul>
 * @author	Rutger van Velzen, Ron Sanders and Marcel Groothuis
 * @version 0.1.0
 * @since	01-11-2015
 */

public class UserInterfaceManager {
		
	/**
	 * This method shows the welcome screen to the user.
	 * @TODO Show the user an option to show all titles up to a certain date. 
	 * Think of: "today, this week, this month"
	 */
	public static void showScreen_Welcome(){
		System.out.println(
				"Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"Type a number");
		SwitchManager.switchWelcome();
		
	}
	public static void showScreen_BlogNavigation(){
		String name = LogicManager.getCurrentLoggedinUserName();
		System.out.println(
				"Welcome "+name+"!\n"+ 
				"1) Do you want to write a new post?\n"+  
				"2) Do you want to read the blog posts?\n"+
				"3) Do you want to signout?\n");
		SwitchManager.switchBlogNavigation();		
	}
	/**
	 * @TODO Guest should be the same as a normal user, but restricted if no one is logged in.
	 */
	public static void showScreen_Guest(){ 
		System.out.println(
				"Welkom Guest\n"+
				"1) Do you want to read all posts?\n"+
				"2) Do you want to sign out?");
				SwitchManager.switchGuest();
		
	}
}
	
	