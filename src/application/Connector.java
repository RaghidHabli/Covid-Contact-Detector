package application;

import java.sql.Connection;
import java.sql.DriverManager;

 
public class Connector {

	public static Connection conn;

	public static void getConnection() throws Exception {
		try {
			String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
			String username = "sql12339199";
			String password = "CT93ij2vnM";
			conn = DriverManager.getConnection(url, username,
					password);
			System.out.println("connected!");

		} catch (Exception e) {
			System.out.println("no connection");
		}
	}
}