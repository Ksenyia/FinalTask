package by.tr.web.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.command.CommandEnum;
import by.tr.web.entity.User;
import by.tr.web.service.RatingService;

/**
 * Servlet Filter implementation class Rating
 */
public class RatingFilter implements Filter {

	private static final String USER = "user";
	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String COMMAND = "command";
	private static final Logger log = LogManager.getLogger(RatingFilter.class);
	private static final String MOVIE = "movie";
	/**
     * Default constructor. 
     */
	
    public RatingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String action = ((HttpServletRequest)request).getParameter(COMMAND);
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		String page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
		int idUser = 0;
		int idMovie = 0;
		User user = (User) session.getAttribute(USER);
		String movie = ((HttpServletRequest)request).getParameter(MOVIE);
		if(user!= null){
			idUser = user.getId();
		}
		if(movie!=null){
			idMovie = Integer.parseInt(movie);
		}
		boolean commandFlag;
		if(action!=null){
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		commandFlag = currentEnum.equals(CommandEnum.RATING);
		}
		else{
			commandFlag = false;
		}
		boolean accessFlag = user!=null && user.isAccessFlag();
		RatingService ratingService = new RatingService();
		if(commandFlag){
			boolean isRattingSatted = ratingService.isRatingSetted(idMovie, idUser);
			if (!accessFlag || isRattingSatted) {
				log.info("action "+action);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} 
			else{
				chain.doFilter(request, response);
			}
		}
		else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
