package by.tr.web.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.tr.web.dao.AuthorizationDAO;
import by.tr.web.entity.User;

public class AuthorizationService {
	
	private static final String UTF_8 = "UTF-8";
	private static final String SHA_256 = "SHA-256";


	public User login(String login, String password){
		AuthorizationDAO authorizationDAO = new AuthorizationDAO();
		String passHashed = passwordHash(password);
		User user = authorizationDAO.login(login, passHashed);
		return user;
	}

	
	  private static String passwordHash(String password)  {
		  try{
		      try {
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
		    	  
		      }
	      
		  }
		  catch (UnsupportedEncodingException e) {
	    	  
	      }
	      return null;
	  }
}
