package by.tr.web.controller.command;

import java.util.ArrayList;
import java.util.List;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;
import by.tr.web.entity.User;
import by.tr.web.service.UsersService;

public class UsersCommand implements ActionCommand {

	private static final String PATH_PAGE_USERS = "path.page.users";

	public String execute(SessionRequestContent content) {
		String page  = ConfigurationManager.getProperty(PATH_PAGE_USERS);
		content.insertLocal();
		
		List<User> users = new ArrayList<User>();
		UsersService usersService = new UsersService(); 
		int pageCount = usersService.getPageCount();
		
		content.setPageCountUser(pageCount);
		
		int pageNumber = content.extractPageNumberUser();
		
		users = usersService.getUsers(pageNumber) ;
		content.insertUsers(users);
		return page;
	}

}
