package by.tr.web.service;

import java.util.HashMap;

import by.tr.web.dao.ReviewDAO;
import by.tr.web.entity.User;

public class ReviewService {

	public HashMap<User, String> showReview(int id )  {
		ReviewDAO reviewDAO = new ReviewDAO();
		return  reviewDAO.showReview(id);
	}
	
	public void addReview(String review,int idMovie,int idUser ){
		ReviewDAO reviewDAO = new ReviewDAO();
		reviewDAO.addReview(review, idMovie, idUser);;
	}
}
