package nl.cerios.blog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.*;
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
	public static User addUser(UserIdentificationRequest newUser) throws Exception { 

		try {
			Connection con = connectionDatabase();
			PreparedStatement posted = con.prepareStatement("INSERT INTO users (username, password) VALUES " + "('"
					+ newUser.getUsername() + "'," + "'" + newUser.getPassword() + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Insert Completed.");
		}
		return getUser(newUser);
	}

	public static Connection connectionDatabase() throws FileNotFoundException, Exception{
		//1. Load the propeties file
		Properties props = new Properties();
		props.load(new FileInputStream("./config.properties.txt"));
		String driver = "com.mysql.jdbc.Driver";
		
		//2. Read the props
		String theUser = props.getProperty("user");
		String thePassword = props.getProperty("password");
		String theDburl = props.getProperty("dburl");
		
		System.out.println("Connecting to database...");
		System.out.println("Database URL: " + theDburl);
		System.out.println("User: " + theUser);
		
		//3. Get a connection to database
		Class.forName(driver);
		Connection con = DriverManager.getConnection(theDburl, theUser, thePassword);
		System.out.println("\nConnection successfull!\n");	
		return con;
	}
	
	public static User getUser(UserIdentificationRequest uir) throws Exception {
		try {
			Connection con = connectionDatabase();
			//where SQL commando 
			//Query
			PreparedStatement statement = con.prepareStatement("SELECT username,password FROM users"); 
			ResultSet result = statement.executeQuery();
			
			User u = new User();
			
			while (result.next()) { //niet meer nodig omdat je er 1 selecteert
				String dbUsername = result.getString("username");
				String dbPassword = result.getString("password");
				System.out.print(dbUsername + " " + dbPassword);
				if(uir.getUsername().equals(dbUsername) && uir.getPassword().equals(dbPassword)){
					u.setUsername(dbUsername);
					return u;
				}
			}
			System.out.println("All records have been selected!");
			return null;
		} catch (Exception e) {
			System.out.println("DBM > getUser(Select): ");
			e.printStackTrace();
			return null;
		}
	}
	public static void newMessage(Message message) throws Exception { 
		try {
			Connection con = connectionDatabase();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO messages (title, body) VALUES " + "('" + message.getTitle()
							+ "'," + "'" + message.getBody() + "')");
			posted.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("DBM > message(Insert): ");
			e.printStackTrace();
		} finally {
			System.out.println("Insert Completed.");
		}
	}
}