package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;

public class MovieCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.movie");
		int idMovie =Integer.parseInt(request.getParameter("movieId"));
		System.out.println(idMovie);
		return page;
	}

}
