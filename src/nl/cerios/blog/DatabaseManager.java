package nl.cerios.blog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
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
		
		Debug.log("Connecting to database...");
		Debug.log("Database URL: " + theDburl);
		Debug.log("User: " + theUser);
				
		Class.forName(driver);
		Connection con = DriverManager.getConnection(theDburl, theUser, thePassword);
		Debug.log("\nConnection successfull!\n");	
		return con;
	}
	
	/**
	 * This method checks if the user exists and if not adds a new user to the MySQL database.
	 * @param uir
	 * @return User
	 * @throws Exception
	 * @TODO Refactor while-loop when "where"-statement is working for querying database
	 */
	public static User addUser(UserIdentificationRequest uir) throws Exception { 
		ResultSet result = null;
		
		try {
			Connection con = connectionDatabase();
			databaseStatement = con.prepareStatement("SELECT * FROM users where username = ?");
			databaseStatement.setString(1, uir.getUsername());
			result = databaseStatement.executeQuery();
			
			while (result.next()){
				return null;
			}			
			databaseStatement = con.prepareStatement("INSERT INTO users (username,password) values (?,?);");
			databaseStatement.setString(1, uir.getUsername());
			databaseStatement.setString(2, uir.getPassword());
			databaseStatement.executeUpdate(); 		
			return getUser(uir);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		ResultSet result = null;
		User user = null;
		
		try {
			Connection con = connectionDatabase();
			databaseStatement = con.prepareStatement("SELECT * FROM users where username = ? and password = ?");
			databaseStatement.setString(1, uir.getUsername());
			databaseStatement.setString(2, uir.getPassword());
			result = databaseStatement.executeQuery();

			while (result.next()){
				String userName = result.getString("username");
				int userID = result.getInt("ID");	
				
				user = new User();
				user.setuserID(userID);
				user.setUsername(userName);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
	}
	
	/**
	 * @param message
	 * @throws Exception
	 * @TODO Refactor foreign key problem in the database
	 */
	public static void newMessage(Message message) throws Exception { 
		try {
			Connection con = connectionDatabase();				
			databaseStatement = con.prepareStatement("INSERT INTO messages (title,body,date,userID) values (?,?,?,?);");
			databaseStatement.setString(1, message.getTitle());
			databaseStatement.setString(2, message.getBody());
			databaseStatement.setDate(3, (Date) message.getDate());
			databaseStatement.setInt(4, message.getUserID());
			databaseStatement.executeUpdate() ; 		
			System.out.println("Message saved in database! \n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param uir
	 * @return User
	 * @throws Exception
	 * @TODO Add setDate
	 */
	public static List<Message> getMessage(){
		ResultSet result = null;
		List<Message> messages = new ArrayList<Message>();
		
		try {
			Connection con = connectionDatabase();
			databaseStatement = con.prepareStatement("SELECT * FROM messages");
			result = databaseStatement.executeQuery();

			while (result.next()){
				Message message = new Message();
				message.setTitle(result.getString("title"));
				message.setBody(result.getString("body"));
				messages.add(message);
			}
			Debug.log("All messages have been selected!");
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			return messages;
		}
	}
}