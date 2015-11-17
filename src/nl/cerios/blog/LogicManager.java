package nl.cerios.blog;
import java.util.List;
import nl.cerios.blog.UserInterfaceManager.CurrentScreen;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.NewUserRequest;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;

public class LogicManager {
	private static User currentLoggedinUser; // some thing like this should exist.
	
	public static String getCurrentLoggedinUserName() {
		return currentLoggedinUser.getUsername();
	}

	public static void main(String[] args) {
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	public static void signIn(UserIdentificationRequest uir){
		// get the matching user out of the database 
		// and store it local as the current user.
		currentLoggedinUser = DatabaseManager.getUser(uir);
		// Do sign in correct stuff
		Debug.log(currentLoggedinUser.getUsername());
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_IN_CORRECT);
		// Or do sign in NOT correct stuff.
	}
	
	public static void signOut(boolean signOut){
		// if go is true, do sign out stuff
		if(signOut){
			currentLoggedinUser = null;
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_CORRECT);
		}else{
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_NOT_CORRECT);
		}
		// else do sign out no correct stuff
	}
	
	public static void signUp(NewUserRequest newUserRequest){
		// Do sign up correct stuff
		DatabaseManager.newUser(newUserRequest);
		currentLoggedinUser = DatabaseManager.newUser(newUserRequest);
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP_CORRECT);
		// or do sign up not correct stuff
	}
	
	public static void AddNewMessage(Message newMessage){
		DatabaseManager.newMessage(newMessage);
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_BLOG_NAVIGATION);
	}
	
	public static List<Message> getAllMessages(){
		List<Message> messages = DatabaseManager.getAllMessages();
		return messages;
	}
}
