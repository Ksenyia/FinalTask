package service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.AuthorizationService;
import by.tr.web.service.MovieService;

public class ServiceTest {
	@Test
    public void authorizationTest() throws SQLException {
    	User user = new User();
    	user.setId(5);
    	user.setLogin("login");
    	user.setAccessFlag(true);
    	user.setAdminFlag(true);
    	user.setStatus(0);
    	user.setEmail("email@gm.com");
    	AuthorizationService authorizationService = new AuthorizationService();
        assertEquals(user,authorizationService.login("login", "123Aa_"));
    }
    @Test
    public void getMoviesTest() throws SQLException {
    	MovieService catalogService = new MovieService();
    	List<Movie> movies = catalogService.getMovies("ru");
        assertEquals(7,movies.get(5).getId());
    }
}
