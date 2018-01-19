package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class StatusDAO {
	private static final int ID_USER = 2;
	private static final int STATUS = 1;
	private static final String UPDATE = "UPDATE movie_rating.users SET users.status = ? WHERE users.id_users= ? ";
	public static boolean updateStatuses(HashMap<Integer, Integer> statuses){
		boolean resalt = true;
		for (Entry<Integer, Integer> pair : statuses.entrySet()) {
			Integer userId = pair.getKey();
			Integer status =  pair.getValue();
			if(updateStatus(userId, status)){
				resalt = false;
			}
		}
		return resalt;
	}
	private static boolean updateStatus(Integer userId, Integer status){
		PreparedStatement preparedStatement = null;
		String insert = UPDATE;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(insert);
		    preparedStatement.setInt(STATUS, status);
		    preparedStatement.setInt(ID_USER, userId);
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

}
