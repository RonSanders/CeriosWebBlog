package nl.cerios.blog;
import java.util.Date;
import java.util.List;
import nl.cerios.blog.UserInterfaceManager.CurrentScreen;

/**
 * <h1>UserInterfaces</h1>
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

//////////////////// To Do ////////////////////
//
//	-Maybe Correct and not-correct should be 
//		an status enum, that adds to the
//		current screen enum (think about it
//		as a sub screen).
//	
//	-Other option, add a value to the enum
//		that indicates the status.
//
//	-Guest should be the same as a normal
//		user but restricted if no one is 
//		logged in.
//
//	-Work out a select post by title option
//		show the actor a list of titles not
//		the whole content of all the messages
//
//	-Make a surge option to find posts by
//		an input string matching a (part of a)
//		title.
//
//	-Order the read posts list buy date.
//
//	-Show the user an option to show all titles
//		up to a certain date. Think of :
//		"today, this week, this month"
//
//////////////////////////////////////////////
public class UserInterfaces {
	public static void showScreen_Welcome(){
		int index = Input.intInput(
				"Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"3) Continue as guest?\n"+
				"Type a number");
		switch(index){
		case 1 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_IN);
			break;
		case 2 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP);
			break;
		case 3 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GUIST);
			break;
		default:
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
		}
	}
	
	public static void showScreen_BlogNavigation(String name){
		int index = Input.intInput(
				"Welcome "+name+"!\n"+ 
				"1) Do you want to write a new post?\n"+
				"2) Do you want to read the blog posts?\n"+
				"3) Do you want to signout?\n");
		switch(index){
		case 1 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_NEW_MESSAGES);
			break;
		case 2 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GET_ALL_MESSAGES);
			break;
		case 3 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT);
			break;
		default:
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
		}
	}
	
	public static void showScreen_Guest(){ // must be one with showScreen_BlogNavigation, contend based on current user == null?
		int index = Input.intInput(
				"Welkom Guest\n"+
				"1) Do you want to read all posts?\n"+
				"2) Do you want to sign out?");
		switch(index){
		case 1 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GET_ALL_MESSAGES);
			break;
		case 2 :
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT);
			break;
		default:
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GUIST);
		}
	}
	
	////////////////////
	//
	//		Sign in
	//
	////////////////////
	public static UserIdentificationRequest showScreen_SignIn(){
		UserIdentificationRequest uir = new UserIdentificationRequest();
		uir.setUsername(Input.stringInput("Enter your userName."));
		uir.setPassword(Input.stringInput("Enter your password."));
		
		return uir;
	}
	
	public static void showScreen_SignIn_Correct(){
		System.out.println("You are signed in!");
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	
	public static void showScreen_SignIn_NotCorrect(){
		System.out.println("You are NOT logged in!");
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	////////////////////
	//
	//		Sign out
	//
	////////////////////
	public static boolean showScreen_SignOut(){
		return Input.yes("Are You sure, you want to sign out?(y/n)");
	}
	
	public static void showScreen_SignOut_Correct(){
		System.out.println("You are signed out!\n Good Bye!\n");
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	//showScreen_SignOut_NotCorrect
	public static void showScreen_SignOut_NotCorrect(){
		System.out.println("Oops!\n We cant sign you out right now.\n");
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	
	////////////////////
	//
	//		Sign up
	//
	////////////////////
	public static NewUserRequest showScreen_SignUp(){
		// show some text and call a function that creates the new user.
		NewUserRequest ur = new NewUserRequest();
		ur.setUsername(Input.stringInput("Enter your username here:"));
		ur.setPassword(Input.stringInput("Enter your password here:"));
		ur.setPassword2(Input.stringInput("Enter your password again here:"));
		
		return ur;
	}
	public static void showScreen_SignUp_Correct(){
		System.out.println("You are signed up!\n");
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	public static void showScreen_SignUp_NotCorrect(){
		System.out.println("Oops!\n We cant sign you up right now.\n");
	}
	
	////////////////////
	//
	//		post
	//
	////////////////////
	
	public static Message showScreen_NewMessage(){
		Message message = new Message();
		message.setTitle(Input.stringInput("Enter your Title:"));
		message.setBody(Input.stringInput("Enter your text:"));
		message.setDate(new Date());
		
		return message;
	}
	
	
	public static void showScreen_ShowMessages(List<Message> messages){
		for (Message message : messages) {
			System.out.println(message.getTitle());
			System.out.println(message.getBody());
			System.out.println();
		}
		
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
}
