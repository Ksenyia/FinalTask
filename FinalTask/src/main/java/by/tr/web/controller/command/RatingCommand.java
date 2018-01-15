package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.Rating;
import by.tr.web.entity.User;

public class RatingCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty("path.page.main");
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if(user!=null){
			int idUser = user.getId();
			int idFilm = Integer.parseInt(request.getParameter("movie"));
			int rating = Integer.parseInt(request.getParameter("rating"));
			Rating.setRating(idUser, rating, idFilm);
		}
		return page;
	}

}
