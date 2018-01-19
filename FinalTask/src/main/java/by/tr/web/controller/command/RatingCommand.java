package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.RatingDAO;
import by.tr.web.entity.User;
import by.tr.web.service.RatingService;

public class RatingCommand implements ActionCommand {

	private static final String RATING2 = "rating";
	private static final String MOVIE = "movie";
	private static final String USER2 = "user";
	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(USER2);
		if(user!=null){
			int idUser = user.getId();
			int idFilm = Integer.parseInt(request.getParameter(MOVIE));
			int rating = Integer.parseInt(request.getParameter(RATING2));
			RatingService ratingService = new RatingService();
			ratingService.setRating(idUser, rating, idFilm);
		}
		return page;
	}

}
