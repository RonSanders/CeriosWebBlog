package nl.cerios.blog;
import java.sql.Date;
import java.util.List;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.UserIdentificationRequest;

public class SwitchManager {
	
	/**
	 * @TODO Other option, add a value to the enum that indicates the status.
	 */
	public static void main(String[] args) {
		SwitchManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
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
	public static void switchCurrentScreen(CurrentScreen currentscreen){
		currentScreen = currentscreen;
	
		CurrentScreen choice = getCurrentScreen();
	
		switch(choice){
			case SHOW_SCREEN_WELCOME :
				UserInterfaceManager.showScreen_Welcome();
				break;
			case SHOW_SCREEN_BLOG_NAVIGATION :
				UserInterfaceManager.showScreen_BlogNavigation();
				break;
			case SHOW_SCREEN_GUEST :
				UserInterfaceManager.showScreen_Guest();
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
				showScreen_ShowMessages();
				break;
			default:
				UserInterfaceManager.showScreen_Welcome();
		}
	}
	
	/**
	 * 
	 * SwitchMenu's
	 */
	public static void switchWelcome(){
		int index = KeyboardManager.intInput();
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
	public static void switchBlogNavigation() {
		int index = KeyboardManager.intInput();
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
	public static void switchGuest() {
		int index = KeyboardManager.intInput();
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
	
	/**
	 * This method is used for signing in.
	 * @TODO Maybe Correct and not-correct should be an status enum, that adds 
	 * to the current screen enum (think about it as a sub screen).
	 */
	
	public static void showScreen_SignIn(){
		UserIdentificationRequest uir = new UserIdentificationRequest();
		uir.setUsername(KeyboardManager.stringInput("Enter your username."));
		uir.setPassword(KeyboardManager.stringInput("Enter your password."));
		LogicManager.signIn(uir);
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP_CORRECT);
	}
	public static void showScreen_SignIn_Correct(){
		System.out.println("You are signed in!");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	public static void showScreen_SignIn_NotCorrect(){
		System.out.println("You are NOT logged in!");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	/**
	 * This method is used for signing out.
	 * @return
	 * @TODO Maybe Correct and not-correct should be an status enum, that adds 
	 * to the current screen enum (think about it as a sub screen).
	 *  	
	 */
	public static void showScreen_SignOut(){
		if(KeyboardManager.yes("Are You sure, you want to sign out?(y/n)"))
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_CORRECT);
		else
			switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_NOT_CORRECT);
	}
	public static void showScreen_SignOut_Correct(){
		System.out.println("You are signed out!\n Good Bye!\n");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	public static void showScreen_SignOut_NotCorrect(){
		System.out.println("Oops!\n We cant sign you out right now.\n");
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	
	/**
	 * This method is used for signing up.
	 */
	public static void showScreen_SignUp(){
		UserIdentificationRequest uir = new UserIdentificationRequest();
		uir.setUsername(KeyboardManager.stringInput("Enter your username here:"));
		String tempSavePassword = KeyboardManager.stringInput("Enter your password here:");
		
		if(tempSavePassword.equals(KeyboardManager.stringInput("Enter your password again here:"))){
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
	
	
	/**
	 * This method is used for posting new messages.
	 */
	public static void showScreen_NewMessage(){
		Message message = new Message();
		message.setTitle(KeyboardManager.stringInput("Enter your Title:"));
		message.setBody(KeyboardManager.stringInput("Enter your text:"));
		message.setDate(new Date(0));
		LogicManager.addNewMessage(message);
	}
	
	/**
	 * This method is used for reading/showing all blog posts.
	 * @TODO Order the read posts list by date.
	 * @TODO Make a surge option to find posts by an input string matching a (part of a) title.
	 */
	public static void showScreen_ShowMessages(){
		List<Message> messages = LogicManager.getAllMessages();
		for (Message message : messages) {
			System.out.println(message.getTitle());
			System.out.println(message.getBody());
			System.out.println();
		}
		switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
}
