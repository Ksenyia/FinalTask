package by.tr.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.tr.web.controller.command.CommandEnum;
import by.tr.web.controller.command.EmptyCommand;

public class ActionFactory {
	private static final Logger log = LogManager.getLogger(ActionFactory.class); 
	public ActionCommand defineCommand(HttpServletRequest request){
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		
		log.info("action "+action);
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}

}
