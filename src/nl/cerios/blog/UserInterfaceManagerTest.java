package nl.cerios.blog;
import java.util.HashMap;

public class UserInterfaceManagerTest {
	private static HashMap<String, Page> dictionary = new HashMap<String, Page>();
	static{
		HashMap<Integer, String> dictionaryPage = new HashMap<Integer, String>();
		dictionaryPage.put(1, "Sign in");
		dictionaryPage.put(2, "Sign up");
		dictionary.put("Welcome",new Page(
				"Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"Type a number",
				dictionaryPage));
		dictionaryPage.clear();

		dictionary.put("Sign in",new Page("Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"Type a number",
				dictionaryPage));
		
		dictionary.put("Sign up",new Page("Welcome to Cerios blog!\n"+
				"1) Sign in?\n"+
				"2) Sign up?\n"+
				"Type a number",
				dictionaryPage));
	
	}
	public static void main(String... arg){
		System.out.println("Start the code!");
		doStuff("Welcome");
	}
	
	public static void doStuff(String pageName){
		System.out.println(dictionary.get(pageName).getPage());
		String nextPageName = dictionary.get(pageName).getNextPage(KeyboardInputs.intInput());
		doStuff(nextPageName);
		
	}
}
class Page{
	HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
	String pageText;
	Page(String pageText, HashMap<Integer, String> dictionary){
		this.pageText = pageText;
		this.dictionary = dictionary;
	}
	public String getPage(){
		return pageText;
	}
	public String getNextPage(int i){
		System.out.println(dictionary.get(i));
		return dictionary.get(i);
	}
	
}