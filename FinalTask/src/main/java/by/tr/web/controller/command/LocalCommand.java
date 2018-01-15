package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;

public class LocalCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page = null;
		String  local = request.getParameter("local");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("local")==null){
			session.setAttribute("local", "ru");
		}
		System.out.println("locale def/old "+session .getAttribute("local"));
		if(local!=null){
			session.setAttribute("local", local);
			page = ConfigurationManager.getProperty("path.page.login");
			System.out.println("locale new"+session.getAttribute("local"));
		}
		return page;
	}

}
