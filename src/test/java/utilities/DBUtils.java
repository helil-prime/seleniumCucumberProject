package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
	private String hostname = "database-1.cbf9mjnqgnfr.us-east-2.rds.amazonaws.com";
	private String port = "3306";
	private String dbname = "stock_trading_tracker";
	private String username = "admin";
	private String password = "Password123!";  // + "?user="   // + "&password="
	private String dburl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
	
	private Connection dbconnect;
	private Statement statement;
	private ResultSet resultset;
	
	public static void main(String[] args) {
		
//		String hostname = "database-1.cbf9mjnqgnfr.us-east-2.rds.amazonaws.com";
//		String port = "3306";
//		String dbname = "stock_trading_tracker";
//		String username = "admin";
//		String password = "Password123!";
//		String dburl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
		
//		try {
//			Connection dbconnect = DriverManager.getConnection(dburl, username, password);
//			System.out.println("Connection established.");
//			Statement statement = dbconnect.createStatement();
//			ResultSet result = statement.executeQuery("SELECT * FROM records;");
//			while (result.next()) {
//				System.out.println(result.getString("symbol"));
//			}
//			
//			dbconnect.close();
//		} catch (SQLException e) {
//			System.out.println("No Connection established.");
//			e.printStackTrace();
//		}
		
		String insertTrade = "INSERT INTO records "
				+ "(user_id, long_short, symbol, open_date, entry_price, close_date, exit_price, gain) VALUES('2', '1', 'TSLA', '2021-06-23', '600', '0000-00-00', '0.00', '0.00')";
		DBUtils utils = new DBUtils();
		utils.insertData(insertTrade);
		
	}
	
	public void selectData(String query) {
		try {
			dbconnect = DriverManager.getConnection(dburl, username, password);
			System.out.println("DB Connection Established.");
			statement = dbconnect.createStatement();
			resultset = statement.executeQuery(query);
			dbconnect.close();
		} catch (SQLException e) {
			System.out.println("No DB Connection Established.");
			e.printStackTrace();
		}
	}
	
	public void insertData(String query) {
		try {
			dbconnect = DriverManager.getConnection(dburl, username, password);
			System.out.println("DB Connection Established.");
			statement = dbconnect.createStatement();
			statement.executeUpdate(query);
			dbconnect.close();
		} catch (SQLException e) {
			System.out.println("No DB Connection Established.");
			e.printStackTrace();
		}
	}
	
	public void updateData(String query) {
		try {
			dbconnect = DriverManager.getConnection(dburl, username, password);
			System.out.println("DB Connection Established.");
			statement = dbconnect.createStatement();
			statement.executeUpdate(query);
			dbconnect.close();
		} catch (SQLException e) {
			System.out.println("No DB Connection Established.");
			e.printStackTrace();
		}
	}
	
	public void deleteData(String query) {
		try {
			dbconnect = DriverManager.getConnection(dburl, username, password);
			System.out.println("DB Connection Established.");
			statement = dbconnect.createStatement();
			statement.executeUpdate(query);
			dbconnect.close();
		} catch (SQLException e) {
			System.out.println("No DB Connection Established.");
			e.printStackTrace();
		}
	}
	
	
	
	
//	<dependency>
//    <groupId>com.github.lwhite1</groupId>
//    <artifactId>tablesaw</artifactId>
//    <version>0.7.7.3</version>
//    </dependency>

}



