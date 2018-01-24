package by.tr.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.dao.RegistrationDAO;
import by.tr.web.entity.Movie;
import by.tr.web.entity.User;
import by.tr.web.service.AuthorizationService;
import by.tr.web.service.MovieService;
import by.tr.web.service.RatingService;
import by.tr.web.service.ReviewService;

public class SessionRequestContent {
	private static final String STATUS = "status";
	private static final String ACCESS = "access";
	private static final String LOCAL = "local";
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String USER = "user";
	private static final String PASSWORD = "pwd1";
	private static final String LOGIN = "usrname";
	private static final String EMAIL = "email";
	private static final String BUTTON = "button";
	private static final String RATING = "rating";
	private static final String MOVIE = "movie";
	private static final String MOVIE_ID = "movieId";
	
	public SessionRequestContent(){
		
	}
	
	public Movie extractMovie(HttpServletRequest request){
		return null;
	}
	
	public String extractButton(HttpServletRequest request){
        String submitName = BUTTON;
        
		String button = request.getParameter(submitName);
		return button;
	}
	
	public void insertMovies(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LOCAL);
		MovieService catalog = new MovieService();
		List<Movie> movies = catalog.getMovies(language);
		String sessionAttribute = "movies"; 
		session.setAttribute(sessionAttribute , movies);
	}
	
	public void insertReview(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		int idMovie = 0;
		if(request.getParameter(MOVIE_ID)!=null){
			Movie movie =(Movie) session.getAttribute(MOVIE);
			idMovie =movie.getId();
		}
		else{
			if(session.getAttribute(MOVIE)!=null){
				idMovie =(Integer) session.getAttribute(MOVIE);
			}
		}
		System.out.println(idMovie);
		ReviewService catalog = new ReviewService();
		HashMap<User,String> reviews = catalog.showReview(idMovie);
		session.setAttribute("map" , reviews);
	}
	
	public void insertUsers (HttpServletRequest request, User user){
		HttpSession session = request.getSession(true);
		session.setAttribute(USER, user);
	}
	
	public User extractAuthorizedUser (HttpServletRequest request){
		
		String name = request.getParameter(PARAM_NAME_LOGIN);
		String password =request.getParameter(PARAM_NAME_PASSWORD);
		AuthorizationService authorizationService = new AuthorizationService();
		User user = authorizationService.login(name,password);		
		return user;
	}
	public User extractUser (HttpServletRequest request){
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(USER);		
		return user;
	}
	public User extractRegisteredUser (HttpServletRequest request){	
		
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		User user = new User();
		RegistrationDAO registrationDAO = new RegistrationDAO();
		user = registrationDAO.register(login, email, password);
		return user;
	}
	
	public void extractStatuses(HttpServletRequest request, HashMap<Integer, Integer> statuses) {
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			Integer userId = null;
			Integer status = null;
			String string = (String) en.nextElement();
			String value = request.getParameter(string);
			String separator = "#";
			String st[] = string.split(separator);
			if(STATUS.equals(st[0])){
				userId = Integer.parseInt(st[1]);
				status = Integer.parseInt(value);
				statuses.put(userId, status);
			}
		}
	}
	
	public void extractAccesses(HttpServletRequest request, HashMap<Integer, Boolean> accesses) {
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			Integer userId = null;
			Boolean accessFlag;
			String string = (String) en.nextElement();
			String value = request.getParameter(string);
			String separator = "#";
			String st[] = string.split(separator);
			if(ACCESS.equals(st[0])){
				accessFlag = Boolean.parseBoolean(value);
				userId = Integer.parseInt(st[1]);
				accesses.put(userId, accessFlag);
			}
		}
	}
	
	public void extractRating(HttpServletRequest request, User user) {
		int idUser = user.getId();
		int idFilm = Integer.parseInt(request.getParameter(MOVIE));
		int rating = Integer.parseInt(request.getParameter(RATING));
		RatingService ratingService = new RatingService();
		ratingService.setRating(idUser, rating, idFilm);
	}
	
	public void insertMovie(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		int idMovie = 0;
		if(request.getParameter(MOVIE_ID)!=null){
			idMovie =Integer.parseInt(request.getParameter(MOVIE_ID));
		}
		else{
			if(session.getAttribute(MOVIE)!=null){
				Movie movie =(Movie) session.getAttribute(MOVIE);
				idMovie =movie.getId();
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<Movie> movies = (ArrayList<Movie>) session.getAttribute("movies");
		MovieService catalogService = new MovieService();
		String language = (String) session.getAttribute(LOCAL);
		Movie movie;
		if(movies==null){
			movie = catalogService.getMovie(idMovie, language);
		}
		else{
			movie = catalogService.findByID(movies, idMovie);
		}
		catalogService.setCountry(movie, language);
		catalogService.setGenre(movie, language);
		session.setAttribute("movie", movie);
	}

	public void inserGenres(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LOCAL);
		MovieService catalog = new MovieService();
		List<String> genres = catalog.getGenres(language);
		String sessionAttribute = "genres"; 
		System.out.println(genres);
		session.setAttribute(sessionAttribute , genres);
	}

	public void insertTypes(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LOCAL);
		MovieService catalog = new MovieService();
		List<String> types = catalog.getTypes(language);
		String sessionAttribute = "types"; 
		session.setAttribute(sessionAttribute , types);
	}

	public void insertCountries(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(LOCAL);
		MovieService catalog = new MovieService();
		List<String> countries = catalog.getCountries(language);
		String sessionAttribute = "countries"; 
		session.setAttribute(sessionAttribute , countries);
	}
}
