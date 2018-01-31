package by.tr.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import by.tr.web.dao.MovieDAO;
import by.tr.web.entity.Movie;

public class MovieService {
	
	public ArrayList<Movie> getMovies(String languge){
		MovieDAO catalogDAO = new MovieDAO();
		ArrayList<Movie> movies = catalogDAO.getMovies(languge);
		return movies;
	}
	public Movie getMovie(int id, String languge, ArrayList<Movie> movies, boolean dbFlag){
		MovieDAO catalogDAO = new MovieDAO();
		Movie movie;
		if(movies==null || movies.size()==0|| dbFlag){
			movie = catalogDAO.getMovie(id, languge);
		}
		else{
			movie = findByID(movies, id);
		}
		return movie;
	}
	public void setGenre(Movie movie, String languge){
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.setGenre(movie, languge);
	}
	public void setCountry(Movie movie, String languge){
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.setCountry(movie, languge);
	}
	private Movie findByID(ArrayList<Movie> movies, int id){
		for(Movie movie: movies){
			if(movie.getId()==id){
				return movie;
			}
		}
		return null;
	}
	public HashMap<Integer, String> getGenres(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getGenres(language);
	}
	public ArrayList<String> getTypes(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getTypes(language);
	}
	public HashMap<Integer, String> getCountries(String language) {
		MovieDAO catalogDAO = new MovieDAO();
		return catalogDAO.getCountries(language);
	}
	public void setMovie(Movie movie, String language ) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.setMovie(movie,language);
	}
	public void updateGenre(int[] genreIDs, int idMovie) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.updateGenre(genreIDs, idMovie);
	}
	public void updateCountry(int[] countryIDs, int idMovie) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.updateCountry(countryIDs, idMovie);
	}
	public void addNewGenres(HashMap<String, String> newGenres, int idMovie) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.addNewGenres(newGenres, idMovie);
		
	}
	public void addNewCountries(HashMap<String, String>  newCountries, int idMovie) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.addNewCountries(newCountries, idMovie);
	}
	public void addNewMovie(Movie movie, String language) {
		MovieDAO catalogDAO = new MovieDAO();
		catalogDAO.addNewMovie(movie, language);
	}
	
	public void deleteMovie(ArrayList<Integer> movies) {
		MovieDAO catalogDAO = new MovieDAO();
		for(int idMovie : movies){
			catalogDAO.deleteMovies(idMovie);
		}
	}

}
