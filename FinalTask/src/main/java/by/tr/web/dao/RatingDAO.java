package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;

public class RatingDAO {
	
	private static final int RATING = 3;
	private static final int ID_USER = 2;
	private static final int ID_FILM = 1;
	private static final String INSERT = "INSERT INTO movie_rating.rating (film_id_film, users_id_user, rating) VALUES (?, ?, ?);";
	private static final String SELECT_COUNT_RATING = "SELECT COUNT(rating) as count  FROM movie_rating.rating where film_id_film = ?;";
	private static final String SELECT_AVG_RATING = "SELECT AVG(rating) as rating FROM movie_rating.rating where film_id_film = ?;";

	public boolean setRating(int idUser,int rating, int idFilm){
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

	public int getRatesCount(int idFilm) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_COUNT_RATING);
		    preparedStatement.setInt(ID_FILM, idFilm);
		    ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
			    return	rs.getInt("count");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public double getAvrageRating(int idFilm) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(SELECT_AVG_RATING,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(ID_FILM, idFilm);
		    ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
			    return	rs.getDouble("rating");
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
