package by.tr.web.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import by.tr.web.dao.RegistrationDAO;
import by.tr.web.entity.User;

public class RegistrationService {
	
	private static final String UTF_8 = "UTF-8";
	private static final String SHA_256 = "SHA-256";
	public static final Logger log = Logger.getLogger(RegistrationService.class);

	public User register(String login,String email, String password){
		RegistrationDAO registrationDAO = new RegistrationDAO();
		String passHashed = passwordHash(password);
		User user = registrationDAO.register(login, email, passHashed);
		return user;
	}
	
	  private static String passwordHash(String password)  {
		  try {
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
		         log.error("encrypt password error", e);
		      }
		  }catch (UnsupportedEncodingException e) {
		         log.error("encrypt password error", e);
		      }
	      return null;
	  }
}
