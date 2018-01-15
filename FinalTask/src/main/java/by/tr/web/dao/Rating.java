package by.tr.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Rating {
	
	public static boolean setRating(int idUser,int rating, int idFilm){
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    String insert = "INSERT INTO movie_rating.rating (film_id_film, users_id_user, rating) VALUES (?, ?, ?);";
	    try {
			preparedStatement = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, idFilm);
		    System.out.println(idUser);
		    preparedStatement.setInt(2, idUser);
		    preparedStatement.setInt(3, rating);
		    preparedStatement.executeUpdate();
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
}

}
