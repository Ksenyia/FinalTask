package by.tr.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.tr.web.entity.Movie;

public class MovieCatalogDAO {

	private static final String REALESE_YEAR = "realese_year";
	private static final String FILMS_RATING = "films_rating";
	private static final String FILMS_ID_FILM = "films_id_film";
	private static final String FTITLE = "ftitle";
	private static final String DEFAULT_LENGUAGE = "ru";
	public MovieCatalogDAO() {
		
	}
	public static List<Movie> getMovies(String languge){
		ConnectionPool pool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    List<Movie> movies = new ArrayList<Movie>();
		try {
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("select films_id_film, g.realese_year,g.films_rating,ftitle"
		    		+ " from films g join "
		    		+ "(select ta.lang_id_lang, td.films_id_film, "
		    		+ "coalesce(ta.ttitle,td.ttitle) as ftitle "
		    		+ "from "
		    		+ "(select * from tfilms where tfilms.lang_id_lang = ?) as td "
		    		+ "left join "
		    		+ "(select * from tfilms where tfilms.lang_id_lang = ?)  as ta "
		    		+ "using(films_id_film)) as t "
		    		+ "on(films_id_film=id_film);");
		    preparedStatement.setString(1, DEFAULT_LENGUAGE);
		    preparedStatement.setString(2, languge);
		    rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Movie movie = getMovie(rs);
				movies.add(movie);
			}
			rs.close();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	private static Movie getMovie(ResultSet rs) throws SQLException {
		Movie movie = new Movie();
		System.out.println(rs.getString(FTITLE));
		movie.setTitle(rs.getString(FTITLE));
		movie.setId(Integer.parseInt(rs.getString(FILMS_ID_FILM)));
		if(rs.getString(FILMS_RATING)!=null){
			movie.setRating(Double.parseDouble(rs.getString(FILMS_RATING)));
		}
		if(rs.getString(REALESE_YEAR)!=null){
			movie.setYear(Date.valueOf(rs.getString(REALESE_YEAR)));
		}
		return movie;
	}
}
