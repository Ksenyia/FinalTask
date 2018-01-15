package by.tr.web.controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.Catalog;
import by.tr.web.entity.Movie;

public class MoviesCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.main");
		HttpSession session = request.getSession(true); 
		List<Movie> movies = null;
		String language = (String) session.getAttribute("local");
		try {
			movies = Catalog.getMovies(language);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sessionAttribute = "movies"; 
		session.setAttribute(sessionAttribute , movies);
		return page;
	}

}
