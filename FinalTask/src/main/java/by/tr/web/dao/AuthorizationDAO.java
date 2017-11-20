package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.security.krb5.EncryptionKey;

public class AuthorizationDAO {

	public AuthorizationDAO() {
		
	}
	public static boolean login(String login, String password) throws SQLException{
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String passHashed = hash(password);
	    boolean check = false;
	    
	    preparedStatement = con.prepareStatement("SELECT login, password FROM dle_users");
	    rs = preparedStatement.executeQuery();
	    
	    if (rs.next()) {
	        if (login.equals(rs.getString("login"))) {
	            if (passHashed.equals(rs.getString("password"))) {
	                check = true;                
	            } else {
	                check = false;
	            }
	        } else {
	            check = false;
	        }
	   return check;
	   //return true;
	}
		return true;
	
}
  
  public static String hash(String password) {
       return passwordHash(password);
  }

 
  public static String passwordHash(String password)  {
     // return EncryptionKey.md5(EncryptionKey.md5(password));
	  return "cv ";
  }

}
