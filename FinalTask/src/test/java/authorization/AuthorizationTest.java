package authorization;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;


import org.junit.Test;

import by.tr.web.dao.AuthorizationDAO;
import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;


public class AuthorizationTest {
    @Test
    public void authorizationTest() throws SQLException {
    	User user = new User();
    	user.setId(5);
    	user.setLogin("login");
    	user.setAccessFlag(true);
    	user.setAdminFlag(true);
    	user.setStatus(0);
    	user.setEmail("email@gm.com");
        assertEquals(user,AuthorizationDAO.login("login", "123Aa_"));
    }
    @Test
    public void getMoviesTest() throws SQLException {
    	List<Movie> movies = MovieCatalogDAO.getMovies("ru");
        assertEquals(7,movies.get(5).getId());
    }
}  
