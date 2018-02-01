package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;
import by.tr.web.entity.User;

public class UsersDAO {
	private static final int COUNT_PER_PAGE = 5;
	private static final int TRUE = 1;
	private static final String STATUS = "status";
	private static final String EMAIL = "email";
	private static final String ID_USERS = "id_users";
	private static final String ACCESS = "access";
	private static final String ADMIN_FLAG = "admin_flag";
	private static final String LOGIN = "login";
	private static final String SELECT_FROM_USERS = "SELECT * FROM users order by id_users limit ?,?";
	private static final String SELECT_STATUS = "SELECT status FROM movie_rating.users where id_users = ?;";
	private static final String UPDATE_ACCESSES = "UPDATE movie_rating.users SET users.access = ? WHERE users.id_users= ? ";
	private static final String UPDATE_STATUSES = "UPDATE movie_rating.users SET users.status = ? WHERE users.id_users= ? ";
	public static final Logger log = Logger.getLogger(UsersDAO.class);

	public List<User> getUsers(int number) {

        List<User> users =  new ArrayList<User>();
        try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
			PreparedStatement preparedStatement = null;
		    ResultSet rs = null;
		    preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
		    preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, COUNT_PER_PAGE );
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


	public boolean updateStatuses(HashMap<Integer, Integer> statuses){
		boolean resalt = true;
		for (Entry<Integer, Integer> pair : statuses.entrySet()) {
			Integer userId = pair.getKey();
			Integer status =  pair.getValue();
			if(!updateStatus(userId, status)){
				resalt = false;
			}
		}
		return resalt;
	}


	public boolean updateAccesses(HashMap<Integer,  Boolean> accesses){
		boolean resalt = true;
		for (Entry<Integer, Boolean> pair : accesses.entrySet()) {
			Integer userId = pair.getKey();
			Boolean access =  pair.getValue();
			if(!updateAccess(userId, access)){
				resalt = false;
			}
		}
		return resalt;
	}
	
	public boolean updateStatus(Integer userId, Integer status){
		PreparedStatement preparedStatement = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_STATUSES);
		    preparedStatement.setInt(1, status);
		    preparedStatement.setInt(2, userId);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
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
	private static boolean updateAccess(Integer userId, Boolean access){

		PreparedStatement preparedStatement = null; 
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ACCESSES);
		    preparedStatement.setBoolean(1, access);
		    preparedStatement.setInt(2, userId);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void changeStatus(int idUser, int statusDifference) {
		ConnectionPool pool;
		try {
			pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
			PreparedStatement preparedStatement = null;
		    ResultSet rs = null;
		    preparedStatement = connection.prepareStatement(SELECT_STATUS);
		    preparedStatement.setInt(1, idUser);
		    rs = preparedStatement.executeQuery();
		    int status;
		    if(rs.next()){
		    	status = rs.getInt("status");
		    	status-=statusDifference;
		    	rs.close();
		    	updateStatus(idUser, status);
		    }
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public int getEntryCount() {
		int count = 0;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(id_users) as count FROM movie_rating.users;");
		    ResultSet rs = preparedStatement.executeQuery();
		    if(rs.next()){
		    	count = rs.getInt("count");
		    }
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}


