package view;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
        public static String url;
		public static String user;
		public static String password;
	
		private static Connection connection;
	
		public static Connection getConexao() {
	
			System.out.print("\n\n User registered and saved on DB!");
			
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				if(connection == null) {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/indian ", "root", "");
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
