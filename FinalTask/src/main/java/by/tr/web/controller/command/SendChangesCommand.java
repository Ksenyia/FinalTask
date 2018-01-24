package by.tr.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.tr.web.controller.ActionCommand;
import by.tr.web.entity.Movie;

public class SendChangesCommand implements ActionCommand {

	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		System.out.println(((Movie)session.getAttribute("movie")).getId());
		System.out.println(request.getParameter("movieId"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("year"));
		System.out.println(request.getParameter("director"));
		System.out.println(request.getParameter("discription"));
		System.out.println(request.getParameter("type"));
		System.out.println(request.getParameter("genre"));
		System.out.println(request.getParameter("types"));
		System.out.println(request.getParameter("genres"));
		System.out.println(request.getParameter("country"));
		System.out.println(request.getParameter("countries"));
		return null;
	}

}
