package nl.cerios.blog;

import nl.cerios.blog.UserInterfaceManager.CurrentScreen;

public class LogicManager {
	private Object currentLoggedinUser; // some thing like this should exist.
	
	public static void start(){
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_WELCOME);
	}
	
	public static void signIn(UserIdentificationRequest uir){
		Debug.log("name: " + uir.getUsername().toString());
		Debug.log("password: " + uir.getPassword().toString());
		
		// get the matching user out of the database 
		// and store it local as the current user.
		
		// Do sign in correct stuff
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_IN_CORRECT);
		
		// Or do sign in NOT correct stuff.
	}
	
	public static void signOut(boolean signOut){
		// if go is true, do sign out stuff
		if(signOut)
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_CORRECT);
		else
			UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_OUT_NOT_CORRECT);
		// else do sign out no correct stuff
	}
	
	public static void signUp(UserRequest ur){
		Debug.log(ur.getUsername());
		Debug.log(ur.getPassword());
		Debug.log(ur.getPassword2());
		
		// Do sign up correct stuff
		UserInterfaceManager.switchCurrentScreen(CurrentScreen.SHOW_SCREEN_SIGN_UP_CORRECT);
		
		// or do sign up not correct stuff
	}
	
	public static void newPost(){
		
	}
	
	public static void readPosts(){
		
	}
}
