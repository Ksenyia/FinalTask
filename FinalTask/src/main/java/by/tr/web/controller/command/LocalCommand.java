package by.tr.web.controller.command;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.SessionRequestContent;

public class LocalCommand implements ActionCommand {

	public String execute(SessionRequestContent content) {
		String page = null;
		String pathPage = content.extractPage();
		content.insertLocal();
		page = ConfigurationManager.getProperty(pathPage);
		return page;
	}

}
