package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;
import by.tr.web.entity.User;


public class RegistrationDAO {
	
	private static final String INSERT_INTO_USERS = "INSERT INTO users (email, login, password) VALUES (?, ?, ?)";
	public static final Logger log = Logger.getLogger(RegistrationDAO.class);
	
	public User register(String login,String email, String passHashed){
		User user = new User();
		PreparedStatement preparedStatement = null;
	    try {
	    	ConnectionPool pool = ConnectionPool.getInstance();
	    	Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(INSERT_INTO_USERS,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setString(1, email);
		    preparedStatement.setString(2, login);
		    preparedStatement.setString(3, passHashed);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		    connection.close();
		    AuthorizationDAO authorizationDAO = new AuthorizationDAO();
			user = authorizationDAO.login(login, passHashed);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			return null;
		}
	    return user;
}

}
