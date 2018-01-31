package by.tr.web.service;

import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.RatingDAO;
import by.tr.web.dao.UsersDAO;

public class RatingService {
	
	public boolean setRating(int idUser,int rating, int idFilm){
		RatingDAO ratingDAO = new RatingDAO();
		if(ratingDAO.setRating(idUser, rating, idFilm)){
			int defaultRatesCaunt = Integer.parseInt(ConfigurationManager.getProperty("rates.count"));
			int rateCount = ratingDAO.getRatesCount(idFilm);
			if(rateCount>=defaultRatesCaunt){
				double avrageRating = ratingDAO.getAvrageRating(idFilm);
				int statusDifference = culculateStatus(rating, avrageRating);
				UsersDAO usersDAO = new UsersDAO();
				usersDAO.changeStatus(idUser, statusDifference);
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public int culculateStatus(int rating, double avrageRating){
		int roundRating = (int)Math.round(avrageRating);
		int ratingDifference = Math.abs(roundRating - rating);
		if(ratingDifference>1){
			return ratingDifference;
		}
		return 0;
	}
	
	public boolean isRatingSetted(int idFilm, int idUser){
		RatingDAO ratingDAO = new RatingDAO();
		return ratingDAO.isRatingSetted(idFilm, idUser);
	
	}
}
