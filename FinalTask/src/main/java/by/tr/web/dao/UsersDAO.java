package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import by.tr.web.entity.User;

public class UsersDAO {
	private static final int TRUE = 1;
	private static final String STATUS = "status";
	private static final String EMAIL = "email";
	private static final String ID_USERS = "id_users";
	private static final String ACCESS = "access";
	private static final String ADMIN_FLAG = "admin_flag";
	private static final String LOGIN = "login";
	private static final String SELECT_FROM_USERS = "SELECT * FROM users";
	public static final Logger log = Logger.getLogger(UsersDAO.class);

	@SuppressWarnings("null")
	public static List<User> getUsers() {
		
		Connection connection;
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users =  null;
        try {
			connection = pool.takeConnection();
			PreparedStatement preparedStatement = null;
		    ResultSet rs = null;
		    preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
		    rs = preparedStatement.executeQuery();
		    
			while(rs.next()) {
				User user = new User();
				user = getUser(rs);
				users.add(user);
			}
			rs.close();
		} catch (ConnectionPoolException e) {
			log.error("ConnectionPool", e);
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("SQL", e);
			e.printStackTrace();
		}
		return users;	
	}

	private static User getUser(ResultSet rs) throws SQLException {
		int id =rs.getInt(ID_USERS);
		boolean accessFlag;
		String email = rs.getString(EMAIL);
		String login = rs.getString(LOGIN);
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
		User user = new User(id, login, email, status, adminFlag, accessFlag);
		return user;
	}
}
