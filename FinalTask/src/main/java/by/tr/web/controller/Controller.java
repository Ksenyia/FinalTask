package by.tr.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final String MESSAGE_NULLPAGE = "message.nullpage";
	private static final String NULL_PAGE = "nullPage";
	private static final String PATH_PAGE_INDEX = "path.page.index";
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Controller.class);

    /**
     * Default constructor. 
     */
    public Controller() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String page = null;
		ActionFactory client = new ActionFactory();
		SessionRequestContent sessionRequestContent = new SessionRequestContent();
		sessionRequestContent.extractValues(request);
		ActionCommand command = client.defineCommand(request);
		page = command.execute(request);
		log.info(page);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
			request.getSession().setAttribute(NULL_PAGE,
					MessageManager.getProperty(MESSAGE_NULLPAGE));
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}