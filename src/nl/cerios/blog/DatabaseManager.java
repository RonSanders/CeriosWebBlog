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

import com.sun.xml.internal.bind.v2.TODO;

import nl.cerios.blog.model.Message;
import nl.cerios.blog.model.User;
import nl.cerios.blog.model.UserIdentificationRequest;


/**
 * @author Rutger van Velzen, Ron Sanders and Marcel Groothuis
 *
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

	/**
	 * This method loads the properties file, reads it and establishes a connection with the database.
	 * @return driver connection
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public static Connection connectionDatabase() throws FileNotFoundException, Exception{
		
		Properties props = new Properties();
		props.load(new FileInputStream("./config.properties.txt"));
		String driver = "com.mysql.jdbc.Driver";
				
		String theUser = props.getProperty("user");
		String thePassword = props.getProperty("password");
		String theDburl = props.getProperty("dburl");
		
		System.out.println("Connecting to database...");
		System.out.println("Database URL: " + theDburl);
		System.out.println("User: " + theUser);
				
		Class.forName(driver);
		Connection con = DriverManager.getConnection(theDburl, theUser, thePassword);
		System.out.println("\nConnection successfull!\n");	
		return con;
	}
	
	/**
	 * @param uir
	 * @return User
	 * @throws Exception
	 * @TODO Refactor while-loop when "where"-statement is working for querying database
	 */
	public static User getUser(UserIdentificationRequest uir) throws Exception {
		try {
			Connection con = connectionDatabase();
			
			PreparedStatement statement = con.prepareStatement("SELECT username,password FROM users"); 
			ResultSet result = statement.executeQuery();
			
			User u = new User();
			
			while (result.next()) { 
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