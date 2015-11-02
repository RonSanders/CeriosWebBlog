package nl.cerios.blog;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
 *
 *         INSERT > https://www.youtube.com/watch?v=0EZlo8hForo
 */

public class DatabaseManager {
	public static void main(String... arg) throws Exception {
		User newUser = new User(); 
		Message newMessage = new Message();
		createTable("users", "username", "password");
		createTable("messages", "title", "body", "date", "username", "userID"); 
		gebruiker(newUser);
		bericht(newMessage);
		get();
	}

	public static void gebruiker(User newUser) throws Exception { 
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("INSERT INTO users (username, password) VALUES " + "('"
					+ newUser.getUsername() + "'," + "'" + newUser.getPassword() + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DBM > gebruiker(Insert): " + e);
		} finally {
			System.out.println("Insert Completed.");
		}
	}
public class ConnectionDatabase{
	//Normally a Main
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		//1. Load the propeties file
		Properties props = new Properties();
		props.load(new FileInputStream("config.properties"));
		
		//2. Read the props
		String theUser = props.getProperty("user");
		String thePassword = props.getProperty("password");
		String theDburl = props.getProperty("dburl");
		
		System.out.println("Connecting to database...");
		System.out.println("Database URL: " + theDburl);
		System.out.println("User: " + theUser);
		
		//3. Get a connection to database
		myConn = DriverManager.getConnection(theDburl, theUser, thePassword);
		
		System.out.println("\nConnection successful!\n");		
	}
}

	public static void bericht(Message newMessage) throws Exception { 
		try {
			Connection con = getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO messages (title, body, date) VALUES " + "('" + newMessage.getTitle()
							+ "'," + "'" + newMessage.getBody() + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DBM > bericht(Insert): " + e);
		} finally {
			System.out.println("Insert Completed.");
		}
	}

	public static List<String> get() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT username,password FROM users"); 
			ResultSet result = statement.executeQuery();
			ArrayList<String> array = new ArrayList<String>();
			while (result.next()) {
				System.out.print(result.getString("username"));
				System.out.print(" ");
				System.out.println(result.getString("password"));
				array.add(result.getString("username"));
			}
			System.out.println("All records have been selected!");
			return array;
		} catch (Exception e) {
			System.out.println("DBM > get(Select): " + e);
		}
		return null;
	}

	/**
	 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
	 *
	 *         for (int i = 0; i < kolomNames.length; i++ ) { String kolomName =
	 *         kolomNames[i]; temp += kolomNames+" varchar(255),";}
	 */

	public static void createTable(String tableName, String... kolomNames) throws Exception {
		try {
			String temp = "";
			for (String kolomName : kolomNames) {
				temp += (kolomName + " varchar(255),");
			}
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS " + tableName
					+ "(id int NOT NULL AUTO_INCREMENT, " + temp + "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBM > Createtable: " + e);
			e.printStackTrace();
			throw e;
		} finally {
			System.out.println("Function Complete!");
		}

	}

	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test";
			String userAccounts_User = "";
			String userAccounts_Password = "";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println("DBM > Connection: " + e);
		}
		return null;
	}
	
	
	
	
	// sudo code
	static User getUser(UserIdentificationRequest uir){
		// find the matching user on the DB and reconstruct a user object.
		
		User user = new User();
		return user;
	}
	static List<Message> getAllMessages(){
		// reconstruct all the message values to a message object and add it to a list
		List<Message> messageList = new ArrayList<Message>();
		
		return messageList;
	}
	static void newUser(User newUser){
		// Add newUser to the table of users
	}
	static void newMessage(Message newMessage){
		// Add newMessage to the table of messages
	}
}
