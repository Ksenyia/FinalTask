package by.tr.web.service;

import by.tr.web.dao.RatingDAO;

public class RatingService {
	
	public boolean setRating(int idUser,int rating, int idFilm){
		RatingDAO ratingDAO = new RatingDAO();
		if(ratingDAO.setRating(idUser, rating, idFilm)){
			return true;
		}
		else{
			return false;
		}
	}

}
