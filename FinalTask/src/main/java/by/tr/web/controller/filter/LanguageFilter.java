package by.tr.web.controller.filter;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LanguageFilter
 */

public class LanguageFilter implements Filter {
	
	private static final String ENCODING = "UTF-8";
	private static final String CONTENT_TYPE = "text/html";
	private static final String DEFAULT_LANGUAGE = "ru";
	private static final String LOCAL = "local";
	private static final Logger log = LogManager.getLogger(LanguageFilter.class);

    /**
     * Default constructor. 
     */
    public LanguageFilter() {
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
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        request.setCharacterEncoding(ENCODING);

		HttpSession session = ((HttpServletRequest) request).getSession(true);
		if(session.getAttribute(LOCAL)==null){
			session.setAttribute(LOCAL, DEFAULT_LANGUAGE);
			String message = "default local";
			log.info(message);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
