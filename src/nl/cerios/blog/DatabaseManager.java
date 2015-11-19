package nl.cerios.blog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;

/**
 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
 *
 *         INSERT > https://www.youtube.com/watch?v=0EZlo8hForo
 */

public class DatabaseManager {
	Connection con = null;
	
	
	public static void addUser(UserIdentificationRequest newUser) throws Exception { 

		try {
			Connection con = connectionDatabase();
			PreparedStatement posted = con.prepareStatement("INSERT INTO users (username, password) VALUES " + "('"
					+ newUser.getUsername() + "'," + "'" + newUser.getPassword() + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DBM > gebruiker(Insert): " + e);
		} finally {
			System.out.println("Insert Completed.");
		}
	}
	
	public static Connection connectionDatabase() throws FileNotFoundException, Exception{
		//1. Load the propeties file
		Properties props = new Properties();
		props.load(new FileInputStream("C:/Users/rsanders/git/CeriosWebBlog/bin/config.properties.gitignore"));
		String driver = "com.mysql.jdbc.Driver";
		
		//2. Read the props
		String theUser = props.getProperty("user");
		String thePassword = props.getProperty("password");
		String theDburl = props.getProperty("dburl");
		
		System.out.println("Connecting to database...");
		System.out.println("Database URL: " + theDburl);
		System.out.println("User: " + theUser);
		
		//3. Get a connection to database
		//myConn = DriverManager.getConnection(theDburl, theUser, thePassword);
		Class.forName(driver);
		Connection con = DriverManager.getConnection(theDburl, theUser, thePassword);
		System.out.println("\nConnection successfull!\n");	
		return con;
	}
	
	public static List<String> getUser() throws Exception {
		try {
			Connection con = connectionDatabase();
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
	
/*	public class ConnectionDatabase{
		Normally a Main
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;*/
		
/*	public static void bericht(Message newMessage) throws Exception { 

		try {
			Connection con = connectionDatabase();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO messages (title, body, date) VALUES " + "('" + "woop"
							+ "'," + "'" + "woop" + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DBM > bericht(Insert): " + e);
		} finally {
			System.out.println("Insert Completed.");
		}
	}
*/
	
/* Create tabel not in use!
	public static void createTable(String tableName, String... kolomNames) throws Exception {

		try {
			String temp = "";
			for (String kolomName : kolomNames) {
				temp += (kolomName + " varchar(255),");
				
			//for (int i = 0; i < kolomNames.length; i++ ) { String kolomName =
	        // 	kolomNames[i]; temp += kolomNames+" varchar(255),";}	
			}
			Connection con = connectionDatabase();
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

	}*/

	/* sudo code 1
	public static User getUser(UserIdentificationRequest uir){
		// find the matching user on the DB and reconstruct a user object.
		
		User user = new User();
		user.setUsername(uir.getUsername());
		
		return user;
	}*/
	/* sudo code 2
	public static List<Message> getAllMessages(){
		// reconstruct all the message values to a message object and add it to a list
		List<Message> messages = new ArrayList<Message>();
		
		Message m = new Message();
		m.setTitle("title");
		m.setBody("Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"
				+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\n"
				+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n"
				+ "It has survived not only five centuries, but also the leap into electronic typesetting, \n"
				+ "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset\n"
				+ "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software \n"
				+ "like Aldus PageMaker including versions of Lorem Ipsum.\n");
		messages.add(m);
		messages.add(m);
		messages.add(m);
		
		return messages;
	}*/
	/* sudo code 3
	public static User newUser(UserIdentificationRequest userRequest) throws FileNotFoundException, Exception{
		// Add newUser to the table of users
		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		Connection con = connectionDatabase();
		
		
		return user;
	}*/
		
	public static void newMessage(Message newMessage){
		// Add newMessage to the table of messages
	}
	
}
