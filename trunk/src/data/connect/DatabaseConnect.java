package data.connect;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DatabaseConnect {
	private Connection connection = null;
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sisys?user=root&password=root");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}
}
