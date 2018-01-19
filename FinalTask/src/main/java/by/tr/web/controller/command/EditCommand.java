package by.tr.web.controller.command;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.service.UsersService;

public class EditCommand implements ActionCommand {

	private static final String STATUS = "status";
	private static final String ACCESS = "access";
	private static final String PATH_PAGE_MAIN = "path.page.main";

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
		request.getParameterNames();
		Enumeration<String> en = request.getParameterNames();
		Integer userId = null;
		Integer status = null;
		Boolean accessFlag;
		HashMap<Integer, Integer> statuses = new HashMap<Integer, Integer>();
		HashMap<Integer, Boolean> accesses = new HashMap<Integer, Boolean>();
		while (en.hasMoreElements()) {
			String string = (String) en.nextElement();
			String value = request.getParameter(string);
			String separator = "#";
			String st[] = string.split(separator);
			if(ACCESS.equals(st[0])){
				accessFlag = Boolean.parseBoolean(value);
				userId = Integer.parseInt(st[1]);
				accesses.put(userId, accessFlag);
			}
			if(STATUS.equals(st[0])){
				userId = Integer.parseInt(st[1]);
				status = Integer.parseInt(value);
				statuses.put(userId, status);
			}
		}
		UsersService usersService = new UsersService();
		usersService.updateAccesses(accesses);
		usersService.updateStatuses(statuses);
		return page;
	}

}
