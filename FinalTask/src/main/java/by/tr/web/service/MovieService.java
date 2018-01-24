package by.tr.web.service;

import java.util.ArrayList;
import java.util.List;

import by.tr.web.dao.MovieDAO;
import by.tr.web.entity.Movie;

public class MovieService {
	
	public ArrayList<Movie> getMovies(String languge){
		MovieDAO catalogDAO = new MovieDAO();
		ArrayList<Movie> movies = catalogDAO.getMovies(languge);
		return movies;
	}
	public Movie getMovie(int id, String languge){
		MovieDAO catalogDAO = new MovieDAO();
		Movie movie = catalogDAO.getMovie(id, languge);
		return movie;
	}
	public void setGenre(Movie movie, String languge){
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.setGenre(movie, languge);
	}
	public void setCountry(Movie movie, String languge){
		MovieDAO catalogDAO = new MovieDAO();
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
	public List<String> getGenres(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getGenres(language);
	}
	public ArrayList<String> getTypes(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getTypes(language);
	}
	public List<String> getCountries(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getCountries(language);
	}

}
