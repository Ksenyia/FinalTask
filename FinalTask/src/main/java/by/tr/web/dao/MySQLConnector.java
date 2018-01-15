package by.tr.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnector {
	
	private static String connection = "jdbc:mysql://127.0.0.1/movie_rating?useSSL=true";
	private static String user = "root";
	private static String password = "password";
	private static String className = "org.gjt.mm.mysql.Driver";

	public MySQLConnector() {
		
		}
	public static Connection connect(){
		Connection con = null;
		try {
			Class.forName(className);
			try {
				con = DriverManager.getConnection(connection,user,password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return con;
	}

}
