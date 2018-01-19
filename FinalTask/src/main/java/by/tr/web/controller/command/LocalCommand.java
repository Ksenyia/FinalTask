package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;

public class LocalCommand implements ActionCommand {

	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String DEFAULT_LANGUGE = "ru";
	private static final String LOCAL = "local";

	public String execute(HttpServletRequest request) {
		String page = null;
		String  local = request.getParameter(LOCAL);
		HttpSession session = request.getSession(true);
		if(session.getAttribute(LOCAL)==null){
			session.setAttribute(LOCAL, DEFAULT_LANGUGE);
		}
		if(local!=null){
			session.setAttribute(LOCAL, local);
			page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
		}
		return page;
	}

}
