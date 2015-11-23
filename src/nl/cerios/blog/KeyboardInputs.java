package nl.cerios.blog;
import java.util.Scanner;

/**
 * Creates a scanner that records the users keystrokes
 * 
 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
 *
 */
public class KeyboardInputs {
	private static Scanner myscanner = new Scanner(System.in);
	static String stringInput(){
		String input = myscanner.nextLine();
		
		return input;
	}
	static String stringInput(String printQuestions){
		System.out.println(printQuestions);
		String input = myscanner.nextLine();

		return input;
	}
	static int intInput(){
		String input = myscanner.nextLine();
		try{
			return Integer.parseInt(input);
		}catch(Exception ex) {
			ex.printStackTrace();
			return intInput();
		}
	}
	static int intInput(String printQuestions){
		System.out.println(printQuestions);
		String input = myscanner.nextLine();
		try{
			return Integer.parseInt(input);
		}catch(Exception ex) {
			ex.printStackTrace();
			return intInput(printQuestions);
		}
	}
	static char charInput(){
		String scannerInput = myscanner.nextLine();
		
		char input = (!scannerInput.isEmpty() ? scannerInput.charAt(0) : '*');
		return input;
	}
	static char charInput(String printQuestions){
		System.out.println(printQuestions);
		String scannerInput = myscanner.nextLine();
		char input = (!scannerInput.isEmpty() ? scannerInput.charAt(0) : '*');
		
		return input;
	}
	static boolean yes(String printQuestions){
		System.out.println(printQuestions);
		char input = KeyboardInputs.charInput();
		
		if(input ==	'y'){
			return true;
		}else if(input == 'n'){
			return false;
		}else{
			return yes(printQuestions);
		}
	}
	public void CloseInputsScanner(){
		myscanner.close();
	}

}