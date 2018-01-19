package by.tr.web.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.tr.web.entity.User;


public class RegistrationDAO {
	
	private static final String UTF_8 = "UTF-8";
	private static final String SHA_256 = "SHA-256";
	private static final String INSERT_INTO_USERS = "INSERT INTO users (email, login, password) VALUES (?, ?, ?)";
	public static final Logger log = Logger.getLogger(RegistrationDAO.class);
	
	public static User logon(String login,String email, String password){
		User user = new User();
		Connection connection;
        ConnectionPool pool = ConnectionPool.getInstance();
        
		PreparedStatement preparedStatement = null;
	    String passHashed = passwordHash(password);
	    try {
	    	connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(INSERT_INTO_USERS,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setString(1, email);
		    preparedStatement.setString(2, login);
		    preparedStatement.setString(3, passHashed);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
			user = AuthorizationDAO.login(login, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			return null;
		}
	    return user;
}

  public static String passwordHash(String password)  {
	  try {
	      try {
	          // String passwordWithSalt = password + Constants.PASSWORD_SALT;
	          String passwordWithSalt = password;
	          MessageDigest md = MessageDigest.getInstance(SHA_256);
	          md.update(passwordWithSalt.getBytes(UTF_8));
	          byte[] digest = md.digest();
	          StringBuilder sb = new StringBuilder();
	          for (byte aDigest : digest) {
	              sb.append(Integer.toHexString(0xff & aDigest));
	          }
	          return sb.toString();
	      } catch (NoSuchAlgorithmException e) {
	         log.error("encrypt password error", e);
	      }
	  }catch (UnsupportedEncodingException e) {
	         log.error("encrypt password error", e);
	      }
      return null;
  }

}
