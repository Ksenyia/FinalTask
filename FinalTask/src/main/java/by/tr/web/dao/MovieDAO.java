package by.tr.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import by.tr.web.dao.connection.pool.ConnectionPool;
import by.tr.web.dao.connection.pool.ConnectionPoolException;
import by.tr.web.entity.Movie;

public class MovieDAO {

	private static final int COUNT_PER_PAGE = 5;
	private static final String INSERT_COUNTRY = "INSERT INTO country_has_films (film_id_films, country_id_country) values (?, ?);";
	private static final String DELETE_COUNTRY_BY_FILM_ID_FILMS = "DELETE FROM country_has_films where film_id_films = ?;";
	private static final String INSERT_GENRES = "insert into films_has_genre (films_id_films, genre_id_genre) values(?,?);";
	private static final String DELETE_GENRES_BY_ID_FILMS = "DELETE from movie_rating.films_has_genre where films_id_films = ?;";
	private static final String UPDATE_MOVIE = "UPDATE films left join tfilms on films_id_film =  id_film SET realese_year = ?, ttitle= ?, ttype= ?, tdirector=?, tdescription=? WHERE lang_id_lang= ? and films_id_film=?;";
	private static final String SELECT_GENRE = "select tgenre.tgerne, tgenre.genre_id_genre from tgenre where lang_id_lang = ? Group by tgerne;";
	private static final String SELECT_COUNTRY = "select tcountry.tname_country, tcountry.country_id_country from tcountry where lang_id_lang = ?  Group by tcountry.tname_country;";
	private static final String SELECT_TYPE = "select ttype from tfilms where lang_id_lang = ? Group by ttype; ";
	private static final String TYPE = "type";
	private static final String DESCRIPTION = "fdescription";
	private static final String DIRECTOR = "director";
	private static final String REALESE_YEAR = "realese_year";
	private static final String FILMS_RATING = "films_rating";
	private static final String FILMS_ID_FILM = "films_id_film";
	private static final String FTITLE = "ftitle";
	private static final String DEFAULT_LENGUAGE = "ru";
	public MovieDAO() {
		
	} 
	public ArrayList<Movie> getMovies(String languge, int number){
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement("select g.realese_year,g.films_rating,ftitle,"+
			" films_id_film, type, director, fdescription "+
   "from films g join "+ 
                "(select ta.lang_id_lang, td.films_id_film, "+
                        "coalesce(ta.ttitle,td.ttitle) as ftitle, "+
                        "coalesce(ta.tdirector,td.tdirector) as director, "+
						"coalesce(ta.tdescription,td.tdescription) as fdescription, "+
						"coalesce(ta.ttype,td.ttype) as type "+
                  "from " + 
                        "(select * from tfilms where tfilms.lang_id_lang = ?) as td "  +
                      "left join  "+
                     "(select * from tfilms where tfilms.lang_id_lang = ?)  as ta "+
					"using(films_id_film)) as t "+
            "on(films_id_film=id_film) order by films_id_film LIMIT ?, ?;");
		    preparedStatement.setString(1, DEFAULT_LENGUAGE);
		    preparedStatement.setString(2, languge);
		    preparedStatement.setInt(3, number);
			preparedStatement.setInt(4, COUNT_PER_PAGE );
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
			Date date = Date.valueOf(rs.getString(REALESE_YEAR));
			movie.setYear(date);
		}
		if(rs.getString(DIRECTOR)!=null){
			movie.setDirector(rs.getString(DIRECTOR));
		}
		if(rs.getString(DESCRIPTION)!=null){
			movie.setDiscription(rs.getString(DESCRIPTION));
		}
		if(rs.getString(TYPE)!=null){
			movie.setType(rs.getString(TYPE));
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
		    preparedStatement = connection.prepareStatement("select g.realese_year,g.films_rating,ftitle,"+
			" films_id_film, type, director, fdescription "+
   "from films g join "+ 
                "(select ta.lang_id_lang, td.films_id_film, "+
                        "coalesce(ta.ttitle,td.ttitle) as ftitle, "+
                        "coalesce(ta.tdirector,td.tdirector) as director, "+
						"coalesce(ta.tdescription,td.tdescription) as fdescription, "+
						"coalesce(ta.ttype,td.ttype) as type "+
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
				if(rs.getString(FILMS_RATING)!=null){
					movie.setRating(Double.parseDouble(rs.getString(FILMS_RATING)));
				}
				if(rs.getString(REALESE_YEAR)!=null){
					System.out.println(rs.getInt(REALESE_YEAR));
					movie.setYear(Date.valueOf(rs.getString(REALESE_YEAR)));
				}
				if(rs.getString(DIRECTOR)!=null){
					movie.setDirector(rs.getString(DIRECTOR));
				}
				if(rs.getString(DESCRIPTION)!=null){
					movie.setDiscription(rs.getString(DESCRIPTION));
				}
				if(rs.getString(TYPE)!=null){
					movie.setType(rs.getString(TYPE));
				}
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
	public HashMap<Integer, String> getGenres(String language) {
		PreparedStatement preparedStatement = null;
		HashMap <Integer,String> genres = new HashMap <Integer,String> ();
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement(SELECT_GENRE);
		    preparedStatement.setString(1, language);
		    rs = preparedStatement.executeQuery();
		    while(rs.next()){
		    	genres.put(rs.getInt("tgenre.genre_id_genre"), rs.getString("tgerne"));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(genres);
		return genres;
	}
	public HashMap<Integer, String> getCountries(String language) {
		PreparedStatement preparedStatement = null;
		HashMap<Integer, String> countries = new HashMap<Integer, String>();
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement(SELECT_COUNTRY);
		    preparedStatement.setString(1, language);
		    rs = preparedStatement.executeQuery();
		    while(rs.next()){
		    	countries.put(rs.getInt("country_id_country"), rs.getString("tname_country"));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}
	public ArrayList<String> getTypes(String language) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> types = new ArrayList<String>();
	    ResultSet rs = null;
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
		    preparedStatement = connection.prepareStatement(SELECT_TYPE);
		    preparedStatement.setString(1, language);
		    rs = preparedStatement.executeQuery();
		    while(rs.next()){
		    	types.add(rs.getString("ttype"));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
	public void setMovie(Movie movie, String language) {
		int year = movie.getYear().toLocalDate().getYear();
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(UPDATE_MOVIE, Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, year);
		    preparedStatement.setString(2, movie.getTitle());
		    preparedStatement.setString(3, movie.getType());
		    preparedStatement.setString(4, movie.getDirector());
		    preparedStatement.setString(5, movie.getDiscription());
		    preparedStatement.setString(6, language);
		    preparedStatement.setInt(7, movie.getId());
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateGenre(int[] genreIDs, int idMovie) {
		deleteGenres(idMovie);
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.takeConnection();
			if(genreIDs.length!=0){
				for(int genreID : genreIDs){
					updateGenre(idMovie, connection, genreID);
				} 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateGenre(int idMovie, Connection connection, int genreID) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(INSERT_GENRES, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, idMovie);
		preparedStatement.setInt(2, genreID);
		preparedStatement.executeUpdate();
	}
	
	private void deleteGenres(int idMovie) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_GENRES_BY_ID_FILMS, Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, idMovie);
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteCountries(int idMovie) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_COUNTRY_BY_FILM_ID_FILMS, Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, idMovie);
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCountry(int[] countriesIDs, int idMovie) {
        try {
        	ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			deleteCountries(idMovie);
			if(countriesIDs.length!=0){
				for(int countryIDs : countriesIDs){
					updateCountry(idMovie, connection, countryIDs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}
	private void updateCountry(int idMovie, Connection connection, int countryIDs) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(INSERT_COUNTRY, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, idMovie);
		preparedStatement.setInt(2, countryIDs);
		preparedStatement.executeUpdate();
	}
	public void addNewGenres(HashMap<String, String> newGenres, int idMovie) {
		PreparedStatement preparedStatement = null;
		try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
	        
		    preparedStatement = connection.prepareStatement("SELECT COUNT(lang_id_lang) as count  FROM tgenre where lang_id_lang = ?;");
		    preparedStatement.setString(1, "ru" );
		    ResultSet rs = preparedStatement.executeQuery();
		    int genreID = 1;
		    if(rs.next()){
		    	genreID = rs.getInt("count");
		    }
		    rs.close();
		    
		    for(Entry<String, String> genres : newGenres.entrySet()){
		    	genreID++;
		    	String genreRU = genres.getKey();
		    	String langRU = "ru";
		    	String genreEN = genres.getValue();
		    	String langEN = "ru";
		    	addGenre(connection, genreID, genreRU, langRU);
		    	addGenre(connection, genreID, genreEN, langEN);
		    	updateGenre(idMovie, connection, genreID);
		    }
	    }catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}
	private void addGenre(Connection connection, int genreID, String genre, String lang) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement("INSERT INTO `movie_rating`.`tgenre` (`lang_id_lang`, `genre_id_genre`, `tgerne`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, lang);
		preparedStatement.setInt(2, genreID);
		preparedStatement.setString(3, genre);
		preparedStatement.executeUpdate();
	}
	public void addNewCountries(HashMap<String, String> newCountries, int idMovie) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
	        
		    preparedStatement = connection.prepareStatement("SELECT COUNT(lang_id_lang) as count  FROM tcountry where lang_id_lang = ?;");
		    preparedStatement.setString(1, "ru");
		    ResultSet rs = preparedStatement.executeQuery();
		    int countryID = 1;
		    if(rs.next()){
		    	countryID = rs.getInt("count");
		    }
		    rs.close();
			for(Entry<String, String> counties : newCountries.entrySet()){
				countryID++;
				String langRU = "ru";
				String countryRU = counties.getKey();
				String langEN = "en";
				String countryEN = counties.getValue();
				addCountry(connection, countryID, langRU, countryRU);
				addCountry(connection, countryID, langEN, countryEN);
			    updateCountry(idMovie, connection, countryID);
	
			} 
		 }catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}
	private void addCountry(Connection connection, int countryID, String lang, String country) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement("INSERT INTO `movie_rating`.`tcountry` (`country_id_country`, `lang_id_lang`, `tname_country`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, countryID);
		preparedStatement.setString(2, lang);
		preparedStatement.setString(3, country);
		preparedStatement.executeUpdate();
	}
	public int addNewMovie(Movie movieRU, Movie movieEN) {
		Integer year = null;
		int movieID = 1;
		if(movieRU.getYear()!=null){
			year = movieRU.getYear().toLocalDate().getYear();
		}
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO `movie_rating`.`films` (`realese_year`) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			if(year!=null){
			    preparedStatement.setInt(1, year);
			}else{
				preparedStatement.setNull(1, java.sql.Types.INTEGER);
			}
		    preparedStatement.executeUpdate();
		    
		    preparedStatement = connection.prepareStatement("SELECT MAX(id_film) as id FROM movie_rating.films;");
		    ResultSet rs = preparedStatement.executeQuery();
		    if(rs.next()){
		    	movieID = rs.getInt("id");
		    }
		    rs.close();
		    String langaugeRU = "ru";
		    String langaugeEN = "en";
		    addNewMovie(movieRU, movieID, connection, langaugeRU);
		    addNewMovie(movieEN, movieID, connection, langaugeEN);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return movieID;
	}
	private void addNewMovie(Movie movie, int movieID, Connection connection, String langauge) throws SQLException {
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement("INSERT INTO `movie_rating`.`tfilms` (`lang_id_lang`, `films_id_film`, `ttitle`, `ttype`, `tdirector`, `tdescription`) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, langauge);
		preparedStatement.setInt(2, movieID);
		preparedStatement.setString(3, movie.getTitle());
		preparedStatement.setString(4, movie.getType());
		preparedStatement.setString(5, movie.getDirector());
		preparedStatement.setString(6, movie.getDiscription());
		preparedStatement.executeUpdate();
	}

	public void deleteMovies(int idMovie) {
		PreparedStatement preparedStatement = null;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM `movie_rating`.`films` WHERE `id_film`=?;", Statement.RETURN_GENERATED_KEYS);
		    preparedStatement.setInt(1, idMovie);
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getEntryCount() {
		int count = 0;
	    try {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.takeConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(id_film) as count FROM movie_rating.films;");
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
