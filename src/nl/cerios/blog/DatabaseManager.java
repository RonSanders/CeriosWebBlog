package nl.cerios.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	public static void bericht(Message newMessage) throws Exception { 
		try {
			Connection con = getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO messages (title, body, date) VALUES " + "('" + newMessage.getTitle()
							+ "'," + "'" + newMessage.getBody() + "'," + "'" + newMessage.getDateTime() + "')");
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
			String username = "";
			String password = "";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println("DBM > Connection: " + e);
		}
		return null;
	}
}
