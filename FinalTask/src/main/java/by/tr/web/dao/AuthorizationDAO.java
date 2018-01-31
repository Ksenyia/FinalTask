package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;
import by.tr.web.entity.User;

public class AuthorizationDAO {

	private static final String STATUS = "status";
	private static final String ADMIN_FLAG = "admin_flag";
	private static final int TRUE = 1;
	private static final String ACCESS = "access";
	private static final String EMAIL = "email";
	private static final String ID_USERS = "id_users";
	private static final String PASSWORD = "password";
	private static final String SELECT_FROM_USERS = "SELECT * FROM users where login = ?";

	public AuthorizationDAO() {
		
	}
	public User login(String login, String passHashed){
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
        try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
		    preparedStatement.setString(1, login);
		    rs = preparedStatement.executeQuery();
			if(rs.next()) {
				if (passHashed.equals(rs.getString(PASSWORD))) {
					User user = getUser(login, rs);
					rs.close();
					return user;
				} 
			}	
			rs.close();
			//connection.close();
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


}
