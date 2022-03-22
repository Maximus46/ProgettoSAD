package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static Connection cn = null;


	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Eccezione di tipo ClassNotFound");
		}

		if (cn == null || cn.isClosed()) {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=");
		}
		return cn;
	}


	public static void closeConnection() throws SQLException {
		if (cn != null && !cn.isClosed()) {
			cn.close();
			cn = null;
		}
	}

}
