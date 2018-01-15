package by.tr.web.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import by.tr.web.entity.User;


public class RegistrationDAO {
	
	public static User logon(String login,String email, String password){
		User user = new User();
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    String passHashed = passwordHash(password);
	    System.out.println(passHashed);
	    String insert = "INSERT INTO users (email, login, password) VALUES (?, ?, ?)";
	    try {
			preparedStatement = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setString(1, email);
		    preparedStatement.setString(2, login);
		    preparedStatement.setString(3, passHashed);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
			user = AuthorizationDAO.login(login, password);
		    return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
}

  public static String passwordHash(String password)  {
	  try {
	      try {
	          // String passwordWithSalt = password + Constants.PASSWORD_SALT;
	          String passwordWithSalt = password;
	          MessageDigest md = MessageDigest.getInstance("SHA-256");
	          md.update(passwordWithSalt.getBytes("UTF-8"));
	          byte[] digest = md.digest();
	          StringBuilder sb = new StringBuilder();
	          for (byte aDigest : digest) {
	              sb.append(Integer.toHexString(0xff & aDigest));
	          }
	          return sb.toString();
	      } catch (NoSuchAlgorithmException e) {
	         // MEGALOG.error("encrypt password error", e);
	      }
	  }catch (UnsupportedEncodingException e) {
	         // MEGALOG.error("encrypt password error", e);
	      }
      return null;
  }

}
