package by.tr.web.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tr.web.entity.User;

public class AuthorizationDAO {

	private static final String STATUS = "status";
	private static final String ADMIN_FLAG = "admin_flag";
	private static final int TRUE = 1;
	private static final String ACCESS = "access";
	private static final String EMAIL = "email";
	private static final String ID_USERS = "id_users";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String SELECT_FROM_USERS = "SELECT * FROM users";

	public AuthorizationDAO() {
		
	}
	public static User login(String login, String password){
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String passHashed = passwordHash(password);
		Connection connection;
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
			connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
		    rs = preparedStatement.executeQuery();
			while(rs.next()) {
				if (login.equals(rs.getString(LOGIN))) {
					if (passHashed.equals(rs.getString(PASSWORD))) {
						User user = getUser(login, rs);
						rs.close();
						return user;
					} 
				}
			}	
			rs.close();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}
	private static User getUser(String login, ResultSet rs) throws SQLException {
		int id =rs.getInt(ID_USERS);
		String email = rs.getString(EMAIL);
		boolean accessFlag;
		if(rs.getByte(ACCESS)==TRUE){
			accessFlag = true;
		}
		else{
			accessFlag = false;
		}
		boolean adminFlag;
		if(rs.getByte(ADMIN_FLAG)==TRUE){
			adminFlag = true;
		}
		else{
			adminFlag = false;
		}
		int status = rs.getInt(STATUS);
		User user = new User(id, login, email,status,adminFlag,accessFlag);
		return user;
	}

  public static String passwordHash(String password)  {
	  try{
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
	    	  
	      }
      
	  }
	  catch (UnsupportedEncodingException e) {
    	  
      }
      return null;
  }

}
