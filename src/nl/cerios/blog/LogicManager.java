package nl.cerios.blog;
import java.util.ArrayList;
import java.util.List;
import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;
/**
 * This is the logic layer, here is where most calculations are done.
 * @author Rutger van Velzen, Ron Sanders, Marcel groothuis
 * @version 0.1.0
 * @since	01-11-2015
 */
public class LogicManager {
	
	private static User currentLoggedinUser;
	
	/**
	 * This returns the user name of the current logged in user.
	 * @return the user name of the currentLoggedinUser
	 */
	public static String getCurrentLoggedinUserName() {
		return currentLoggedinUser.getUsername();
	}
	
	/**
	 * 
	 * @param uir
	 */
	public static void signIn(UserIdentificationRequest uir){
		Debug.log(currentLoggedinUser.getUsername());
	}
	/**
	 * 
	 * @param signOut
	 */
	public static void signOut(boolean signOut){
		if(signOut){
			currentLoggedinUser = null;
		}
	}
	/**
	 * <p>This sends an <code>{@link nl.cerios.blog.model.UserIdentificationRequest} </code> object to the <code>{@link nl.cerios.blog.DatebaseManager}</code> 
	 * and sets the class variable <code>{@link LogicManager#currentLoggedinUser}</code> to the User object return value.</p>
	 * @param uir Takes an <code>{@link nl.cerios.blog.model.UserIdentificationRequest} </code> object with the values to make a new user.
	 */
	public static void signUp(UserIdentificationRequest uir){
		try{
			currentLoggedinUser = DatabaseManager.addUser(uir);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return
	 */
	public static List<Message> getAllMessages(){
		List<Message> messages = new ArrayList<>();
		return messages;
	}
	/**
	 * 
	 * @param message
	 */
	public static void addNewMessage(Message message) {
		try{
			DatabaseManager.newMessage(message);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}