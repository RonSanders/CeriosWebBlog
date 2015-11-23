package nl.cerios.blog;
import java.util.ArrayList;
import java.util.List;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;

public class LogicManager {
	
	private static User currentLoggedinUser;
	
	public static String getCurrentLoggedinUserName() {
		return currentLoggedinUser.getUsername();
	}
	public static void signIn(UserIdentificationRequest uir){
		Debug.log(currentLoggedinUser.getUsername());
	}
	public static void signOut(boolean signOut){
		if(signOut){
			currentLoggedinUser = null;
		}
	}
	public static void signUp(UserIdentificationRequest uir){
		try{
			currentLoggedinUser = DatabaseManager.addUser(uir);
		}catch(Exception e){
		}
	}
	public static List<Message> getAllMessages(){
		List<Message> messages = new ArrayList<>();
		return messages;
	}
	public static void addNewMessage(Message message) {
		try{
			DatabaseManager.newMessage(message);
		}catch (Exception e){
		}
	}
}