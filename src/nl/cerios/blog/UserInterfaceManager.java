package nl.cerios.blog;

public class UserInterfaceManager {
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
				UserInterfaces.showScreen_Welcome();
				break;
			case SHOW_SCREEN_BLOG_NAVIGATION :
				UserInterfaces.showScreen_BlogNavigation(LogicManager.getCurrentLoggedinUserName());
				break;
			case SHOW_SCREEN_GUEST :
				UserInterfaces.showScreen_Guest();
				break;
		
			case SHOW_SCREEN_SIGN_IN :
				LogicManager.signIn(UserInterfaces.showScreen_SignIn());
				break;
			case SHOW_SCREEN_SIGN_IN_CORRECT :
				UserInterfaces.showScreen_SignIn_Correct();
				break;
			case SHOW_SCREEN_SIGN_IN_NOT_CORRECT :
				UserInterfaces.showScreen_SignIn_NotCorrect();
				break;
			
			case SHOW_SCREEN_SIGN_OUT :
				LogicManager.signOut(UserInterfaces.showScreen_SignOut());
				break;
			case SHOW_SCREEN_SIGN_OUT_CORRECT :
				UserInterfaces.showScreen_SignOut_Correct();
				break;
			case SHOW_SCREEN_SIGN_OUT_NOT_CORRECT :
				UserInterfaces.showScreen_SignOut_NotCorrect();
				break;
		
			case SHOW_SCREEN_SIGN_UP :
				LogicManager.signUp(UserInterfaces.showScreen_SignUp());
				break;
			case SHOW_SCREEN_SIGN_UP_CORRECT :
				UserInterfaces.showScreen_SignUp_Correct();
				break;
			case SHOW_SCREEN_SIGN_UP_NOT_CORRECT :
				UserInterfaces.showScreen_SignUp_NotCorrect();
				break;
	
			case SHOW_SCREEN_NEW_MESSAGES :
				LogicManager.AddNewMessage(UserInterfaces.showScreen_NewMessage());
				break;
			case SHOW_SCREEN_GET_ALL_MESSAGES :
				UserInterfaces.showScreen_ShowMessages(LogicManager.getAllMessages());
				break;
			default:
				UserInterfaces.showScreen_Welcome();
		}
	}
}
