package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;
import by.tr.web.entity.User;

public class ReviewDAO {
	public HashMap<User, String> showReview(int id )  {
		PreparedStatement preparedStatement = null;
	    HashMap<User, String> reviews = new HashMap<User, String>();
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("SELECT users.login, rating.review, users.status "+
	"FROM users INNER JOIN rating  ON users.id_users= rating.users_id_user "+
    "where rating.film_id_film = ? AND review IS NOT NULL " );
		    preparedStatement.setInt(1, id);
		    rs = preparedStatement.executeQuery();
		    while(rs.next()){
		    	User user = new User();
		    	user.setLogin(rs.getString("login"));
		    	user.setStatus(rs.getInt("status"));
		    	String review = rs.getString("review");
		    	reviews.put(user, review);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}
}
