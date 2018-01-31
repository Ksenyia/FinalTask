package by.tr.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.entity.Movie;
import by.tr.web.entity.User;

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
	private static final String USERS = "users";
	private static final String ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
	
	
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String[]> requestParameters;
	private HashMap<String, Object> sessionAttributes;
	
	public SessionRequestContent(){
	
	}
	
	public void extractValue(HttpServletRequest request){
		System.out.println("extractValue begin :");
		requestAttributes = new HashMap<String, Object>();
		requestParameters = new HashMap<String, String[]>();
		sessionAttributes = new HashMap<String, Object>();
		Enumeration<String> parameterNames = request.getParameterNames();
		Enumeration<String> attributeNames = request.getAttributeNames();
		HttpSession session = request.getSession(true);
		Enumeration<String> sessionAttributeNames=session.getAttributeNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String[] parameter = request.getParameterValues(parameterName);
			System.out.println("parameterName : " + parameterName + " parameter : " + parameter);
			requestParameters.put(parameterName, parameter);
		}
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attribute = request.getAttribute(attributeName);
			System.out.println("attributeName : " + attributeName + " attribute : " + attribute);
			requestAttributes.put(attributeName, attribute);
		}
		while (sessionAttributeNames.hasMoreElements()) {
			String sessionAttributeName = sessionAttributeNames.nextElement();
			Object sessionAttribute = session.getAttribute(sessionAttributeName);
			System.out.println("sessionAttributeName : " + sessionAttributeName + " sessionAttribute : " + sessionAttribute);
			sessionAttributes.put(sessionAttributeName, sessionAttribute);
		}
		System.out.println("extractValue end  :");
	}	
	
	public void insertValue(HttpServletRequest request){
		System.out.println("insertValue begin :");
		HttpSession session = request.getSession(true);
		System.out.println("Request parameters :");
		requestParameters.entrySet();
		for(Entry<String, String[]>parameterEntry:requestParameters.entrySet()){
			String name = parameterEntry.getKey();
			String[] values = parameterEntry.getValue();
			System.out.println("name : " + name);
			for(String value:values){
				System.out.print("values : " + value + " ");
			}
			System.out.println();
		}
		System.out.println("Request attributes :");
		for(Entry<String, Object> attributeEntry:requestAttributes.entrySet()){
			String name = attributeEntry.getKey();
			Object value = attributeEntry.getValue();
			System.out.println("name : " + name + " value : " + value);
			request.setAttribute(name, value);
		}
		System.out.println("Session attributes :");
		for(Entry<String, Object> attributeEntry:sessionAttributes.entrySet()){
			String name = attributeEntry.getKey();
			Object value = attributeEntry.getValue();
			System.out.println("name : " + name + " value : " + value);
			session.setAttribute(name, value);
		}
		System.out.println("insertValue end :");
	}
	
	public Movie extractRuMovie(){
		Movie movie = new Movie();
		
		int id = 0;
		if(sessionAttributes.get("movieId")!=null){
			id = (Integer) sessionAttributes.get("movieId");
		}
		String title = requestParameters.get("titleRU")[0];
		Date year = null;
		if(requestParameters.get("year")[0]!=null&&!requestParameters.get("year")[0].isEmpty()){
			year = Date.valueOf(requestParameters.get("year")[0]);
		}
		String director = requestParameters.get("directorRU")[0];
		String discription = requestParameters.get("discriptionRU")[0];
		String type;
		String[] types = requestParameters.get("typeRU");
		if(types.length>0){
			type = types[0];
		}else{
			type = types[1];
		}
		movie.setId(id);
		movie.setTitle(title);
		movie.setYear(year);
		movie.setDirector(director);
		movie.setDiscription(discription);
		movie.setType(type);
		return movie;
	}
	
	public Movie extractEnMovie(){
		Movie movie = new Movie();
		int id = 0;
		if(sessionAttributes.get("movieId")!=null){
			id = (Integer) sessionAttributes.get("movieId");
		}
		String title = requestParameters.get("titleEN")[0];
		Date year = null;
		if(requestParameters.get("year")[0]!=null&&!requestParameters.get("year")[0].isEmpty()){
			year = Date.valueOf(requestParameters.get("year")[0]);
		}
		String director = requestParameters.get("directorEN")[0];
		String discription = requestParameters.get("discriptionEN")[0];
		String type;
		String[] types = requestParameters.get("typeEN");
		if(types.length>0){
			type = types[0];
		}else{
			type = types[1];
		}
		movie.setId(id);
		movie.setTitle(title);
		movie.setYear(year);
		movie.setDirector(director);
		movie.setDiscription(discription);
		movie.setType(type);
		return movie;
	}

	public int[] extractCountries() {
		String[] countries = requestParameters.get("country");
		if(countries!=null){
			int length = countries.length;
			int[] countryIDs =  new int[length] ;
			for(int i = 0; i<length; i++){
				if(!"other".equalsIgnoreCase(countries[i])){
					countryIDs[i] = Integer.parseInt(countries[i]);
				}
			}
			return countryIDs;
		}
		else{
			return null;
		}
	}
	
	public int[] extractGenres() {
		String[] genres = requestParameters.get("genre");
		if(genres!=null){
			int length = genres.length;
			int[] genreIDs =  new int[length] ;
			for(int i = 0; i<length; i++){
				if(!"other".equalsIgnoreCase(genres[i])){
					genreIDs[i] = Integer.parseInt(genres[i]);
				}
			}
			return genreIDs;
		}
		else{
			return null;
		}
	}
	
	public HashMap<String, String> extractNewGenres(){
		HashMap<String, String> genres = new HashMap<String, String>();
		String[] newGenreRU  = requestParameters.get("newGenreRU");
		String[] newGenreEN  = requestParameters.get("newGenreEN");
		if(newGenreRU!=null){
			for (int i = 0; i< newGenreRU.length; i++){
				genres.put(newGenreRU[i], newGenreEN[i]);
			}
		}
		return genres;
	}
	
	public HashMap<String, String> extractNewCountries(){
		HashMap<String, String> countries = new HashMap<String, String>(); 
		String[] newCountryRU  = requestParameters.get("newCountryRU");
		String[] newCountryEN  = requestParameters.get("newCountryEN");
		if(newCountryRU!=null){
			for (int i = 0; i< newCountryRU.length; i++){
				countries.put(newCountryRU[i], newCountryEN[i]);
			}
		}
		return countries;
	}
	
	public String extractButton(){
		String button = requestParameters.get(BUTTON)[0];
		return button;
	}
	
	public void insertMovies(List<Movie> movies){
		String sessionAttribute = "movies"; 
		sessionAttributes.put(sessionAttribute, movies);
	}
	
	public void insertReview(HashMap<User,String> reviews){
		sessionAttributes.put("map" , reviews);
	}
	
	public void insertUser ( User user){
		sessionAttributes.put(USER, user);
	}
	
	public void insertUsers ( List<User> users){
		sessionAttributes.put(USERS, users);
	}
	
	public User extractAuthorizedUser (){
		
		String login = requestParameters.get(PARAM_NAME_LOGIN)[0];
		String password =requestParameters.get(PARAM_NAME_PASSWORD)[0];
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		
		return user;
	}
	
	public User extractUser (){
		User user = (User) sessionAttributes.get(USER);
		return user;
	}
	
	public User extractRegisteredUser (){	
		
		String login = requestParameters.get(LOGIN)[0];
		String password = requestParameters.get(PASSWORD)[0];
		String email = requestParameters.get(EMAIL)[0];
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
		
		return user;
	}
	
	public void extractStatuses(HashMap<Integer, Integer> statuses) {
		Set<String> requestNames = requestParameters.keySet();
		for(String name : requestNames){
			Integer userId = null;
			Integer status = null;
			String value = requestParameters.get(name)[0];
			String separator = "#";
			String st[] = name.split(separator);
			if(STATUS.equals(st[0])){
				userId = Integer.parseInt(st[1]);
				status = Integer.parseInt(value);
				statuses.put(userId, status);
			}
		}
	}
	
	public void extractAccesses(HashMap<Integer, Boolean> accesses) {
		Set<String> requestNames = requestParameters.keySet();
		for(String name : requestNames){
			Integer userId = null;
			Boolean accessFlag;
			String value = requestParameters.get(name)[0];
			String separator = "#";
			String st[] = name.split(separator);
			if(ACCESS.equals(st[0])){
				accessFlag = Boolean.parseBoolean(value);
				userId = Integer.parseInt(st[1]);
				accesses.put(userId, accessFlag);
			}
		}
	}
	
	public int[] extractRating() {
		int[] data = new int[2];
		data[0] = Integer.parseInt(requestParameters.get(MOVIE)[0]);
		data[1] = Integer.parseInt(requestParameters.get(RATING)[0]);
		return data;
	}
	
	public void insertMovie(Movie movie) {	
		sessionAttributes.put("movie", movie);
	}
	
	public void insertMovie(Movie movie, String language) {	
		sessionAttributes.put("movie"+ language.toUpperCase(), movie);
		
	}

	public ArrayList<Movie> extractMovies() {
		@SuppressWarnings("unchecked")
		ArrayList<Movie> movies = (ArrayList<Movie>) sessionAttributes.get("movies");
		return movies;
	}

	public int extractMovieID() {
		int idMovie = 0;
		if(requestParameters.get(MOVIE_ID)!=null){
			idMovie = Integer.parseInt(requestParameters.get(MOVIE_ID)[0]) ;
		}
		else{
			if(sessionAttributes.get(MOVIE)!=null){
				Movie movie = (Movie) sessionAttributes.get(MOVIE);
				idMovie = movie.getId();
			}
		}
		return idMovie;
	}

	public void inserGenres(HashMap<Integer, String> genres) {
		String sessionAttribute = "genres"; 
		sessionAttributes.put(sessionAttribute , genres);
	}

	public void insertENTypes(List<String> types) {
		String sessionAttribute = "typesEN"; 
		sessionAttributes.put(sessionAttribute , types);
	}
	
	public void insertRUTypes(List<String> types) {
		String sessionAttribute = "typesRU"; 
		sessionAttributes.put(sessionAttribute , types);
	}

	public void insertCountries(HashMap<Integer, String> countries) {
		String sessionAttribute = "countries"; 
		sessionAttributes.put(sessionAttribute , countries);
	}

	public void insertMessage(String property) {
		sessionAttributes.put(ERROR_LOGIN_PASS_MESSAGE, property);
	}

	public String extractPage() {
		return requestParameters.get("page")[0];
	}

	public void insertLocal() {
		String local;
		if(requestParameters.containsKey(LOCAL)){
			local = requestParameters.get(LOCAL)[0];
		}
		else{
			local = (String) sessionAttributes.get(LOCAL);
		}
		sessionAttributes.put(LOCAL, local);
	}

	public String extractLocal() {
		return (String) sessionAttributes.get(LOCAL);
		
	}

	public String extractReview() {
		return requestParameters.get("review")[0];
	}

	public ArrayList<Integer> extractDeleteMovied() {
		System.out.println("del begin");
		String[] movies = requestParameters.get("delete");
		ArrayList<Integer> movieIDs =  new ArrayList<Integer>() ;
		if(movies!=null){
			for(String movie:movies){
				int id = Integer.parseInt(movie);
				if(id!=0){
					movieIDs.add(id);
				}
			}
			System.out.println("del end");
		}
		return movieIDs;
	}
	
}
