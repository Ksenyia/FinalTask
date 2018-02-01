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

import static by.tr.web.controller.ControllerConstant.*;

public class SessionRequestContent {
	
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String[]> requestParameters;
	private HashMap<String, Object> sessionAttributes;
	
	public SessionRequestContent(){
		requestAttributes = new HashMap<String, Object>();
		requestParameters = new HashMap<String, String[]>();
		sessionAttributes = new HashMap<String, Object>();
	}
	
	public void extractValue(HttpServletRequest request){
		Enumeration<String> parameterNames = request.getParameterNames();
		Enumeration<String> attributeNames = request.getAttributeNames();
		HttpSession session = request.getSession(true);
		Enumeration<String> sessionAttributeNames=session.getAttributeNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String[] parameter = request.getParameterValues(parameterName);
			requestParameters.put(parameterName, parameter);
		}
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attribute = request.getAttribute(attributeName);
			requestAttributes.put(attributeName, attribute);
		}
		while (sessionAttributeNames.hasMoreElements()) {
			String sessionAttributeName = sessionAttributeNames.nextElement();
			Object sessionAttribute = session.getAttribute(sessionAttributeName);
			sessionAttributes.put(sessionAttributeName, sessionAttribute);
		}
	}	
	
	public void insertValue(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		requestParameters.entrySet();
		for(Entry<String, Object> attributeEntry:requestAttributes.entrySet()){
			String name = attributeEntry.getKey();
			Object value = attributeEntry.getValue();
			request.setAttribute(name, value);
		}
		for(Entry<String, Object> attributeEntry:sessionAttributes.entrySet()){
			String name = attributeEntry.getKey();
			Object value = attributeEntry.getValue();
			session.setAttribute(name, value);
		}
	}
	
	public Movie extractMovie(String language){
		String prefix = language.toUpperCase();
		Movie movie = new Movie();
		int id = 0;
		if(sessionAttributes.get(MOVIE_ID)!=null){
			id = (Integer) sessionAttributes.get(MOVIE_ID);
		}
		String title = requestParameters.get(TITLE+prefix)[0];
		Date year = null;
		if(requestParameters.get(YEAR)[0]!=null&&!requestParameters.get(YEAR)[0].isEmpty()){
			year = Date.valueOf(requestParameters.get(YEAR)[0]);
		}
		String director = requestParameters.get(DIRECTOR+prefix)[0];
		String discription = requestParameters.get(DISCRIPTION+prefix)[0];
		String type;
		String[] types = requestParameters.get(TYPE+prefix);
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
		String[] countries = requestParameters.get(COUNTRY);
		if(countries!=null){
			int length = countries.length;
			int[] countryIDs =  new int[length] ;
			for(int i = 0; i<length; i++){
				if(!OTHER.equalsIgnoreCase(countries[i])){
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
		String[] genres = requestParameters.get(GENRE);
		if(genres!=null){
			int length = genres.length;
			int[] genreIDs =  new int[length] ;
			for(int i = 0; i<length; i++){
				if(!OTHER.equalsIgnoreCase(genres[i])){
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
		String[] newGenreRU  = requestParameters.get(NEW_GENRE_RU);
		String[] newGenreEN  = requestParameters.get(NEW_GENRE_EN);
		if(newGenreRU!=null){
			for (int i = 0; i< newGenreRU.length; i++){
				genres.put(newGenreRU[i], newGenreEN[i]);
			}
		}
		return genres;
	}
	
	public HashMap<String, String> extractNewCountries(){
		HashMap<String, String> countries = new HashMap<String, String>(); 
		String[] newCountryRU  = requestParameters.get(NEW_COUNTRY_RU);
		String[] newCountryEN  = requestParameters.get(NEW_COUNTRY_EN);
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
		String sessionAttribute = MOVIES; 
		sessionAttributes.put(sessionAttribute, movies);
	}
	
	public void insertReview(HashMap<User,String> reviews){
		sessionAttributes.put(MAP , reviews);
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
		sessionAttributes.put(MOVIE, movie);
	}
	
	public void insertMovie(Movie movie, String language) {	
		sessionAttributes.put(MOVIE+ language.toUpperCase(), movie);
		
	}

	public ArrayList<Movie> extractMovies() {
		@SuppressWarnings("unchecked")
		ArrayList<Movie> movies = (ArrayList<Movie>) sessionAttributes.get(MOVIES);
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
		String sessionAttribute = GENRES; 
		sessionAttributes.put(sessionAttribute , genres);
	}

	public void insertTypes(List<String> types, String language) {
		String sessionAttribute = TYPES + language.toUpperCase(); 
		sessionAttributes.put(sessionAttribute , types);
	}
	public void insertCountries(HashMap<Integer, String> countries) {
		String sessionAttribute = COUNTRIES; 
		sessionAttributes.put(sessionAttribute , countries);
	}

	public void insertMessage(String property) {
		sessionAttributes.put(ERROR_LOGIN_PASS_MESSAGE, property);
	}

	public String extractPage() {
		return requestParameters.get(PAGE)[0];
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
		return requestParameters.get(REVIEW)[0];
	}

	public ArrayList<Integer> extractDeleteMovied() {
		String[] movies = requestParameters.get(DELETE);
		ArrayList<Integer> movieIDs =  new ArrayList<Integer>() ;
		if(movies!=null){
			for(String movie:movies){
				int id = Integer.parseInt(movie);
				if(id!=0){
					movieIDs.add(id);
				}
			}
		}
		return movieIDs;
	}

	public int extractPageNumber() {
		int pageNumber = 1;
		if(requestParameters.containsKey("pageNumber")){
			pageNumber =Integer.parseInt(requestParameters.get("pageNumber")[0]);
			sessionAttributes.put("pageNumber", pageNumber);
		}
		else{
			if(sessionAttributes.containsKey("pageNumber")){
				pageNumber= (Integer) sessionAttributes.get("pageNumber");
			}
			sessionAttributes.put("pageNumber", pageNumber);
		}
		return pageNumber;
	}

	public void setPageCount(int pageCount) {
		sessionAttributes.put("pageCount", pageCount);
	}

	public void setPageCountUser(int pageCount) {
		sessionAttributes.put("pageCountUsers", pageCount);
		
	}

	public int extractPageNumberUser() {
		int pageNumber = 1;
		if(requestParameters.containsKey("pageNumberUsers")){
			pageNumber =Integer.parseInt(requestParameters.get("pageNumberUsers")[0]);
			sessionAttributes.put("pageNumberUsers", pageNumber);
		}
		else{
			if(sessionAttributes.containsKey("pageNumberUsers")){
				pageNumber= (Integer) sessionAttributes.get("pageNumberUsers");
			}
			sessionAttributes.put("pageNumberUsers", pageNumber);
		}
		return pageNumber;
	}
	
}
