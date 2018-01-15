package by.tr.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.tr.web.entity.Movie;

public class Catalog {

	public Catalog() {
		
	}
	public static List<Movie> getMovies(String languge) throws SQLException{
		Connection con = MySQLConnector.connect();
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    List<Movie> movies = new ArrayList<Movie>();
	    preparedStatement = con.prepareStatement("select films_id_film, g.realese_year,g.films_rating,ftitle"
	    		+ " from films g join "
	    		+ "(select ta.lang_id_lang, td.films_id_film, "
	    		+ "coalesce(ta.ttitle,td.ttitle) as ftitle "
	    		+ "from "
	    		+ "(select * from tfilms where tfilms.lang_id_lang = ?) as td "
	    		+ "left join "
	    		+ "(select * from tfilms where tfilms.lang_id_lang = ?)  as ta "
	    		+ "using(films_id_film)) as t "
	    		+ "on(films_id_film=id_film);");
	    preparedStatement.setString(1, "ru");
	    preparedStatement.setString(2, languge);
	    rs = preparedStatement.executeQuery();
		while(rs.next()) {
			Movie movie = new Movie();
			System.out.println(rs.getString("ftitle"));
			movie.setTitle(rs.getString("ftitle"));
			movie.setId(Integer.parseInt(rs.getString("films_id_film")));
			if(rs.getString("films_rating")!=null){
				movie.setRating(Double.parseDouble(rs.getString("films_rating")));
			}
			else{
				movie.setRating(null);
			}
			if(rs.getString("realese_year")!=null){
				movie.setYear(Date.valueOf(rs.getString("realese_year")));
			}
			else{
				movie.setYear(null);
			}
			movies.add(movie);
			
		}
		rs.close();
		return movies;
	}
}
