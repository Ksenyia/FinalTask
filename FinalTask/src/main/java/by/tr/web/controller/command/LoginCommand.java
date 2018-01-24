package by.tr.web.controller.command;


import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.MessageManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.User;

public class LoginCommand implements ActionCommand {
	
	private static final String ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
	private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";
	private static final String PATH_PAGE_REGISTER = "path.page.register";
	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String PATH_PAGE_MAIN = "path.page.main";
	private static final String REGISTER_BUTTON = "Register";
	private static final String LOGIN_BUTTON = "login";

	public String execute(HttpServletRequest request) {
		String page = null;
		SessionRequestContent content = new SessionRequestContent();
		String button = content.extractButton(request);
		if((button!=null)&&button.equals(LOGIN_BUTTON)){
			User user = content.extractAuthorizedUser(request);
			if(user!=null){
				content.insertMovies(request);
				content.insertUsers(request, user);
				page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
			}
			else{
				request.setAttribute(ERROR_LOGIN_PASS_MESSAGE,MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
				page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
			}
		}
		if((button!=null)&&button.equals(REGISTER_BUTTON)){
			page = ConfigurationManager.getProperty(PATH_PAGE_REGISTER);
		}
		return page;
	}

}
