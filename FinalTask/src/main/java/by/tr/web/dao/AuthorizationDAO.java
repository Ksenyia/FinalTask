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

	public AuthorizationDAO() {
		
	}
	public static User login(String login, String password) throws SQLException{
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String passHashed = passwordHash(password);
	    User user = new User();
	    preparedStatement = con.prepareStatement("SELECT * FROM users");
	    rs = preparedStatement.executeQuery();
	    
		while(rs.next()) {
			System.out.println(rs.getString("login"));
			if (login.equals(rs.getString("login"))) {
				if (passHashed.equals(rs.getString("password"))) {
					int id =rs.getInt("id_users");
					String email = rs.getString("email");
					boolean accessFlag;
					System.out.println(rs.getByte("access") + " "+ rs.getByte("admin_flag"));
					if(rs.getByte("access")==1){
						accessFlag = true;
					}
					else{
						accessFlag = false;
					}
					boolean adminFlag;
					if(rs.getByte("admin_flag")==1){
						adminFlag = true;
					}
					else{
						adminFlag = false;
					}
					int status = rs.getInt("status");
					user.setAccessFlag(accessFlag);
					user.setAdminFlag(adminFlag);
					user.setId(id);
					user.setEmail(email);
					user.setLogin(login);
					user.setStatus(status);
					rs.close();
					return user;
				} 
			}
		}	
		rs.close();
		return null;
	
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
