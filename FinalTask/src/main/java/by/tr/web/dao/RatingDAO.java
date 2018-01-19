package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RatingDAO {
	
	private static final int RATING = 3;
	private static final int ID_USER = 2;
	private static final int ID_FILM = 1;
	private static final String INSERT = "INSERT INTO movie_rating.rating (film_id_film, users_id_user, rating) VALUES (?, ?, ?);";

	public static boolean setRating(int idUser,int rating, int idFilm){
		PreparedStatement preparedStatement = null;
	    String insert = INSERT;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(ID_FILM, idFilm);
		    preparedStatement.setInt(ID_USER, idUser);
		    preparedStatement.setInt(RATING, rating);
		    preparedStatement.executeUpdate();
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}

}
