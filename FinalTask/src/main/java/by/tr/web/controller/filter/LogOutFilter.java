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

import by.tr.web.controller.ConfigurationManager;
import by.tr.web.controller.command.CommandEnum;

/**
 * Servlet Filter implementation class LogOutFilter
 */
public class LogOutFilter implements Filter {
	
	private static final String PATH_PAGE_LOGIN = "path.page.login";
	private static final String COMMAND = "command";

    /**
     * Default constructor. 
     */
    public LogOutFilter() {
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
		
		boolean commandFlag;
		if(action!=null){
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		commandFlag = currentEnum.equals(CommandEnum.LOGOUT);
		}
		else{
			commandFlag = false;
		}
		
		if (commandFlag) {
			session.invalidate();
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
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
