package dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;


import org.junit.Test;

import by.tr.web.dao.AuthorizationDAO;
import by.tr.web.dao.MovieDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;


public class DAOTest {
    @Test
    public void authorizationTest() throws SQLException {
    	User user = new User();
    	user.setId(5);
    	user.setLogin("login");
    	user.setAccessFlag(true);
    	user.setAdminFlag(true);
    	user.setStatus(0);
    	user.setEmail("email@gm.com");
    	AuthorizationDAO authorizationDAO = new AuthorizationDAO();
        assertEquals(user,authorizationDAO.login("login", "123Aa_"));
    }
    @Test
    public void getMoviesTest() throws SQLException {
    	MovieDAO catalogDAO = new MovieDAO();
    	List<Movie> movies = catalogDAO.getMovies("ru");
        assertEquals(7,movies.get(5).getId());
    }
    @Test
    public void getMovieTest() throws SQLException {
    	MovieDAO catalogDAO = new MovieDAO();
    	Movie movie = catalogDAO.getMovie(5, "ru");
        assertEquals((Double)9.5, movie.getRating());
    }
    @Test
    public void setGenreTest() throws SQLException {
    	MovieDAO catalogDAO = new MovieDAO();
    	Movie movie = catalogDAO.getMovie(5, "ru");
    	catalogDAO.setGenre(movie, "ru");
    	catalogDAO.setCountry(movie, "ru");
        assertEquals((Double)9.5, movie.getRating());
    }
}  
