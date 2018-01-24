package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class LocalCommand implements ActionCommand {

	private static final String DEFAULT_LANGUGE = "ru";
	private static final String LOCAL = "local";

	public String execute(HttpServletRequest request) {
		String page = null;
		String pathPage = request.getParameter("page");
		HttpSession session = request.getSession(true);
		String  local = request.getParameter(LOCAL);
		SessionRequestContent content = new SessionRequestContent();
		System.out.println(local);
		if(local==null){
			session.setAttribute(LOCAL, DEFAULT_LANGUGE);
			System.out.println(local + "null");
		}
		else{
			session.setAttribute(LOCAL, local);
			System.out.println(local);
		}
		page = ConfigurationManager.getProperty(pathPage);
		if(pathPage.equals("path.page.main")){
			System.out.println(local);
			content.insertMovies(request);
		}
		if(pathPage.equals("path.page.movie")){
			content.insertMovie(request);
		}
		return page;
	}

}
