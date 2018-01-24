package by.tr.web.controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.entity.User;
import by.tr.web.service.UsersService;

public class UsersCommand implements ActionCommand {

	private static final String USERS = "users";
	private static final String PATH_PAGE_USERS = "path.page.users";

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty(PATH_PAGE_USERS);
		List<User> users = new ArrayList<User>();
		UsersService usersService = new UsersService(); 
		users = usersService.getUsers() ;
		HttpSession session = request.getSession(true);
		session.setAttribute(USERS , users);
		return page;
	}

}
