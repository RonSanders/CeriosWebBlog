package nl.cerios.blog;
import java.util.List;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;

public class LogicManager {
	// test test test. This is a test! Ron can you see this????
	private static User currentLoggedinUser; // some thing like this should exist.
	
	public static String getCurrentLoggedinUserName() {
		return currentLoggedinUser.getUsername();
	}
	public static void signIn(UserIdentificationRequest uir){
		// get the matching user out of the database 
		// and store it local as the current user.
		currentLoggedinUser = DatabaseManager.getUser(uir);
		// Do sign in correct stuff
		Debug.log(currentLoggedinUser.getUsername());
		// Or do sign in NOT correct stuff.
	}
	
	public static void signOut(boolean signOut){
		// if go is true, do sign out stuff
		if(signOut){
			currentLoggedinUser = null;
		}else{
		}
		// else do sign out no correct stuff
	}
	
	public static void signUp(UserIdentificationRequest uir){
		// Do sign up correct stuff
		DatabaseManager.newUser(uir);
		currentLoggedinUser = DatabaseManager.newUser(uir);
		// or do sign up not correct stuff
	}
	
	public static void AddNewMessage(Message newMessage){
		DatabaseManager.newMessage(newMessage);
	}
	
	public static List<Message> getAllMessages(){
		List<Message> messages = DatabaseManager.getAllMessages();
		return messages;
	}
}
