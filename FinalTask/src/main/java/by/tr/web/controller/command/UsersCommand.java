package by.tr.web.controller.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.controller.ConfigurationManager;
import by.tr.web.dao.UsersDAO;
import by.tr.web.entity.User;

public class UsersCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		String page  = ConfigurationManager.getProperty("path.page.users");
		System.out.println("Work");
		List<User> users = new ArrayList<User>();
		try {
			users = UsersDAO.getUsers() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("users" , users);
		return page;
	}

}
