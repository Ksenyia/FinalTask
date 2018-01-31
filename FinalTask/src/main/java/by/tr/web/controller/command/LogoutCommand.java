package by.tr.web.controller.command;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class LogoutCommand implements ActionCommand {

	private static final String PATH_PAGE_INDEX = "path.page.index";

	public String execute(SessionRequestContent content) {
		String page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
		content.insertLocal();
		return page;
	}

}
