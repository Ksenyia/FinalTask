package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Status {
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
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
		String insert = "UPDATE movie_rating.users SET users.status = ? WHERE users.id_users= ? ";
		try {
			preparedStatement = con.prepareStatement(insert);
		    preparedStatement.setInt(1, status);
		    preparedStatement.setInt(2, userId);
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
