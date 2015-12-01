package nl.cerios.blog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	static PreparedStatement databaseStatement = null;
	
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
	 * This method adds a new user to the MySQL database.
	 * @param uir
	 * @return User
	 * @throws Exception
	 * @TODO Refactor while-loop when "where"-statement is working for querying database
	 */
	
	public static User addUser(UserIdentificationRequest uir) throws Exception { 

		try {
			//1. Get a connection
			Connection con = connectionDatabase();
			
			//2.Prepared statement						
			databaseStatement = con.prepareStatement("INSERT INTO users (username,password) values (?,?);");
			
			//PreparedStatement posted = con.prepareStatement("INSERT INTO users (username, password) VALUES " + "('"
			//		+ uir.getUsername() + "'," + "'" + uir.getPassword() + "')");
			
			//3. Set the parameters
			databaseStatement.setString(1, uir.getUsername());
			databaseStatement.setString(2, uir.getPassword());
			
			//4. Execute SQL query
			databaseStatement.executeUpdate(); 		
			return getUser(uir);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Insert Completed.");
		}
		return getUser(uir);
	}
	
	/**
	 * @param uir
	 * @return User
	 * @throws Exception
	 * @TODO Refactor while-loop when "where"-statement is working for querying database
	 */
	public static User getUser(UserIdentificationRequest uir) throws Exception {
		try {
			//1. Get a connection
			Connection con = connectionDatabase();
			
			//2.Prepared statement
			databaseStatement = con.prepareStatement("SELECT * FROM users where username = ? and password = ?");
			//PreparedStatement statement = con.prepareStatement("SELECT username,password FROM users"); 
			
			//3. Set the parameters
			databaseStatement.setString(1, uir.getUsername());
			databaseStatement.setString(2, uir.getPassword());
			
			//4. Execute SQL query
			ResultSet result = databaseStatement.executeQuery();
			
			//5. Display the result set
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
	
	/**
	 * @param message
	 * @throws Exception
	 * @TODO Refactor foreign key problem in the database
	 */
	public static void newMessage(Message message) throws Exception { 
		try {
			//1. Get a connection
			Connection con = connectionDatabase();
			
			//2.Prepared statement						
			databaseStatement = con.prepareStatement("INSERT INTO messages (title,body,date,userID) values (?,?,?,?);");
			
			//PreparedStatement posted = con
			//		.prepareStatement("INSERT INTO messages (title, body) VALUES " + "('" + message.getTitle()
			//				+ "'," + "'" + message.getBody() + "')");
			
			//3. Set the parameters
			databaseStatement.setString(1, message.getTitle());
			databaseStatement.setString(2, message.getBody());
			databaseStatement.setDate(3, (Date) message.getDate());
			databaseStatement.setInt(4, message.getUserID());
			
			//4. Execute SQL query
			databaseStatement.executeUpdate(); 		
			
			//posted.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("DBM > message(Insert): ");
			e.printStackTrace();
		} finally {
			System.out.println("Insert Completed.");
		}
	}
}