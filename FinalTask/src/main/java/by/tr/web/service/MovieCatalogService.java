package by.tr.web.service;

import java.util.ArrayList;
import java.util.List;

import by.tr.web.dao.MovieCatalogDAO;
import by.tr.web.entity.Movie;

public class MovieCatalogService {
	
	public List<Movie> getMovies(String languge){
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		List<Movie> movies = catalogDAO.getMovies(languge);
		return movies;
	}
	public Movie getMovie(int id, String languge){
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		Movie movie = catalogDAO.getMovie(id, languge);
		return movie;
	}
	public void setGenre(Movie movie, String languge){
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		catalogDAO.setGenre(movie, languge);
	}
	public void setCountry(Movie movie, String languge){
		MovieCatalogDAO catalogDAO = new MovieCatalogDAO();
		catalogDAO.setCountry(movie, languge);;
	}
	public Movie findByID(ArrayList<Movie> movies, int id){
		for(Movie movie: movies){
			if(movie.getId()==id){
				return movie;
			}
		}
		return null;
	}

}
