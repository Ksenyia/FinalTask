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
	public List<Movie> getMovies(String languge){
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    List<Movie> movies = new ArrayList<Movie>();
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
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
	public Movie getMovie(int id, String languge)  {
		Movie movie = new Movie();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("select g.realese_year,g.films_rating,ftitle, films_id_film "+
   "from films g join "+ 
                "(select ta.lang_id_lang, td.films_id_film, "+
                        "coalesce(ta.ttitle,td.ttitle) as ftitle "+
                  "from " + 
                        "(select * from tfilms where tfilms.lang_id_lang = ?) as td "  +
                      "left join  "+
                     "(select * from tfilms where tfilms.lang_id_lang = ?)  as ta "+
					"using(films_id_film)) as t "+
            "on(films_id_film=id_film) "+
            "where films_id_film = ?;");
		    preparedStatement.setString(1, DEFAULT_LENGUAGE);
		    preparedStatement.setString(2, languge);
		    preparedStatement.setInt(3, id);
		    rs = preparedStatement.executeQuery();
		    if(rs.next()){
		    	movie.setTitle(rs.getString(FTITLE));
		    	movie.setId(id);
		    }
		if(rs.getString(FILMS_RATING)!=null){
				movie.setRating(Double.parseDouble(rs.getString(FILMS_RATING)));
			}
			if(rs.getString(REALESE_YEAR)!=null){
				movie.setYear(Date.valueOf(rs.getString(REALESE_YEAR)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	public void setGenre(Movie movie, String languge)  {
		int id = movie.getId();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("SELECT ta.tgerne "+
		    		"FROM films INNER JOIN films_has_genre INNER JOIN "+
		            "((select * from tgenre where lang_id_lang = ?) as td "+
		                          "left join "+  
		    	"(select * from tgenre where lang_id_lang = ?)  as ta "+
		    					"using(`genre_id_genre`) )"+
		        "ON films.id_film = films_id_films  AND ta.genre_id_genre = films_has_genre.genre_id_genre "+
		        "WHERE id_film = ?;" );
		    preparedStatement.setString(1, DEFAULT_LENGUAGE);
		    preparedStatement.setString(2, languge);
		    preparedStatement.setInt(3, id);
		    rs = preparedStatement.executeQuery();
		    ArrayList<String> genres = new ArrayList<String>();
		    while(rs.next()){
		    	genres.add(rs.getString("tgerne"));
		    }
		    movie.setGenres(genres);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCountry(Movie movie, String languge)  {
		int id = movie.getId();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("SELECT ta.tname_country "+
	"FROM films INNER JOIN country_has_films INNER JOIN "+
    "((select * from tcountry where lang_id_lang = ?) as td "+
                      "left join "+ 
	"(select * from tcountry where lang_id_lang = ?)  as ta "+
					"using(`country_id_country`) )"+
                    "ON films.id_film = film_id_films  "+
                     "AND country_has_films.`country_id_country` = ta.`country_id_country` "+
                     "WHERE id_film = ?;" );
		    preparedStatement.setString(1, DEFAULT_LENGUAGE);
		    preparedStatement.setString(2, languge);
		    preparedStatement.setInt(3, id);
		    rs = preparedStatement.executeQuery();
		    ArrayList<String> countries = new ArrayList<String>();
		    while (rs.next()){
		    	countries.add(rs.getString("tname_country"));
		    }
		    movie.setCountries(countries);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
