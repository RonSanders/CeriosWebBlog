package nl.cerios.blog;
import java.util.Date;
import java.util.List;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.UserIdentificationRequest;

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

/*/////////////////// To Do ////////////////////
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
/////////////////////////////////////////////*/
public class UserInterfaceManager {
	public static void main(String[] args) {
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	public enum CurrentScreen {
		SHOW_SCREEN_WELCOME, 
		SHOW_SCREEN_BLOG_NAVIGATION, 
		SHOW_SCREEN_GUEST,
		SHOW_SCREEN_SIGN_IN, 
		SHOW_SCREEN_SIGN_IN_CORRECT, 
		SHOW_SCREEN_SIGN_IN_NOT_CORRECT, 
		SHOW_SCREEN_SIGN_OUT, 
		SHOW_SCREEN_SIGN_OUT_CORRECT,
		SHOW_SCREEN_SIGN_OUT_NOT_CORRECT,
		SHOW_SCREEN_SIGN_UP, 
		SHOW_SCREEN_SIGN_UP_CORRECT, 
		SHOW_SCREEN_SIGN_UP_NOT_CORRECT,
		SHOW_SCREEN_NEW_MESSAGES,
		SHOW_SCREEN_SHOW_MESSAGES,
		SHOW_SCREEN_GET_ALL_MESSAGES
	}
	private static CurrentScreen currentScreen = CurrentScreen.SHOW_SCREEN_WELCOME;
	public static CurrentScreen getCurrentScreen() {
		return currentScreen;
	}
	public static void switchCurrentScreen(CurrentScreen currentScreen) {
		UserInterfaceManager.currentScreen = currentScreen;
	
		switch(getCurrentScreen()){
			case SHOW_SCREEN_WELCOME :
				showScreen_Welcome();
				break;
			case SHOW_SCREEN_BLOG_NAVIGATION :
				showScreen_BlogNavigation(LogicManager.getCurrentLoggedinUserName());
				break;
			case SHOW_SCREEN_GUEST :
				showScreen_Guest();
				break;
		
			case SHOW_SCREEN_SIGN_IN :
				showScreen_SignIn();
				break;
			case SHOW_SCREEN_SIGN_IN_CORRECT :
				showScreen_SignIn_Correct();
				break;
			case SHOW_SCREEN_SIGN_IN_NOT_CORRECT :
				showScreen_SignIn_NotCorrect();
				break;
			
			case SHOW_SCREEN_SIGN_OUT :
				showScreen_SignOut();
				break;
			case SHOW_SCREEN_SIGN_OUT_CORRECT :
				showScreen_SignOut_Correct();
				break;
			case SHOW_SCREEN_SIGN_OUT_NOT_CORRECT :
				showScreen_SignOut_NotCorrect();
				break;
		
			case SHOW_SCREEN_SIGN_UP :
				showScreen_SignUp();
				break;
			case SHOW_SCREEN_SIGN_UP_CORRECT :
				showScreen_SignUp_Correct();
				break;
			case SHOW_SCREEN_SIGN_UP_NOT_CORRECT :
				showScreen_SignUp_NotCorrect();
				break;
	
			case SHOW_SCREEN_NEW_MESSAGES :
				showScreen_NewMessage();
				break;
			case SHOW_SCREEN_GET_ALL_MESSAGES :
				showScreen_ShowMessages(LogicManager.getAllMessages());
				break;
			default:
				showScreen_Welcome();
		}
	}
	public static void showScreen_Welcome(){
		int index = Input.intInput(
				"Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"Type a number");
		switch(index){
		case 1 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_IN);
			break;
		case 2 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP);
			break;
		default:
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
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
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_NEW_MESSAGES);
			break;
		case 2 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GET_ALL_MESSAGES);
			break;
		case 3 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT);
			break;
		default:
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
		}
	}
	public static void showScreen_Guest(){ // must be one with showScreen_BlogNavigation, contend based on current user == null?
		int index = Input.intInput(
				"Welkom Guest\n"+
				"1) Do you want to read all posts?\n"+
				"2) Do you want to sign out?");
		switch(index){
		case 1 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GET_ALL_MESSAGES);
			break;
		case 2 :
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT);
			break;
		default:
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_GUEST);
		}
	}
	
	////////////////////
	//
	//		Sign in
	//
	////////////////////
	public static void showScreen_SignIn(){
		UserIdentificationRequest uir = new UserIdentificationRequest();
		uir.setUsername(Input.stringInput("Enter your userName."));
		uir.setPassword(Input.stringInput("Enter your password."));

	}
	public static void showScreen_SignIn_Correct(){
		System.out.println("You are signed in!");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	public static void showScreen_SignIn_NotCorrect(){
		System.out.println("You are NOT logged in!");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
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
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	//showScreen_SignOut_NotCorrect
	public static void showScreen_SignOut_NotCorrect(){
		System.out.println("Oops!\n We cant sign you out right now.\n");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	
	////////////////////
	//
	//		Sign up
	//
	////////////////////
	public static void showScreen_SignUp(){
		UserIdentificationRequest uir = new UserIdentificationRequest();
		uir.setUsername(Input.stringInput("Enter your username here:"));
		String tempSavePassword = Input.stringInput("Enter your password here:");
		
		if(tempSavePassword.equals(Input.stringInput("Enter your password again here:"))){
			uir.setPassword(tempSavePassword);
			LogicManager.signUp(uir);
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP_CORRECT);
		}else{
			System.out.println("Your password did not match!");
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP_NOT_CORRECT);
		}
	}
	public static void showScreen_SignUp_Correct(){
		System.out.println("You are signed up!\n");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	public static void showScreen_SignUp_NotCorrect(){
		System.out.println("Oops!\n We cant sign you up right now.\n");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	////////////////////
	//
	//		post
	//
	////////////////////
	public static void showScreen_NewMessage(){
		Message message = new Message();
		message.setTitle(Input.stringInput("Enter your Title:"));
		message.setBody(Input.stringInput("Enter your text:"));
		message.setDate(new Date());
		LogicManager.addNewMessage(message);
	}
	public static void showScreen_ShowMessages(List<Message> messages){
		for (Message message : messages) {
			System.out.println(message.getTitle());
			System.out.println(message.getBody());
			System.out.println();
		}
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
}
