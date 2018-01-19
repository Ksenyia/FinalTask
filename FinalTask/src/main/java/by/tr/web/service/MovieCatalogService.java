package by.tr.web.service;

import java.util.List;

import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.entity.Movie;

public class MovieCatalogService {
	
	public List<Movie> getMovies(String languge){
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		List<Movie> movies = catalogDAO.getMovies(languge);
		return movies;
	}

}
