package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Accsess {
	public static boolean updateAccesses(HashMap<Integer,  Boolean> accesses){
		boolean resalt = true;
		for (Entry<Integer, Boolean> pair : accesses.entrySet()) {
			Integer userId = pair.getKey();
			Boolean access =  pair.getValue();
			if(updateAccess(userId, access)){
				resalt = false;
			}
		}
		return resalt;
	}
	private static boolean updateAccess(Integer userId, Boolean access){
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null; 
		String insert = "UPDATE movie_rating.users SET users.access = ? WHERE users.id_users= ? ";
		try {
			preparedStatement = con.prepareStatement(insert);
		    preparedStatement.setBoolean(1, access);
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
