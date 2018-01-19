package by.tr.web.controller.command;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.AccsessDAO;
import by.tr.web.dao.StatusDAO;

public class EditCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty("path.page.main");
		request.getParameterNames();
		System.out.println(request.getParameter("user"));
		System.out.println(request.getParameter("access"));
		Enumeration<String> en = request.getParameterNames();
		Integer userId = null;
		Integer status = null;
		Boolean accessFlag;
		HashMap<Integer, Integer> statuses = new HashMap<Integer, Integer>();
		HashMap<Integer, Boolean> accesses = new HashMap<Integer, Boolean>();
		while (en.hasMoreElements()) {
			String string = (String) en.nextElement();
			String value = request.getParameter(string);
			System.out.println("name: "+string+" value: "+request.getParameter(string));
			String st[] = string.split("#");
			if("access".equals(st[0])){
				accessFlag = Boolean.parseBoolean(value);
				userId = Integer.parseInt(st[1]);
				accesses.put(userId, accessFlag);
			}
			if("status".equals(st[0])){
				userId = Integer.parseInt(st[1]);
				status = Integer.parseInt(value);
				statuses.put(userId, status);
			}
		}
		AccsessDAO.updateAccesses(accesses);
		StatusDAO.updateStatuses(statuses);
		return page;
	}

}
