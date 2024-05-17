package src.resources.config;
import java.sql.*;

public class DBConnection {
	public static String url;
	public static String user;
	public static String password;

	private static Connection connection;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {}
	}

	public static Connection getConexao() {

		System.out.print("\n\n Connected !");
		
		try {
			if(connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "");
				return connection;
			}
			else {
				return connection;
			}
		}/*  
		catch(ClassNotFoundException cex){
			cex.printStackTrace();
			return null;
		}*/
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}  
	}
	
}
