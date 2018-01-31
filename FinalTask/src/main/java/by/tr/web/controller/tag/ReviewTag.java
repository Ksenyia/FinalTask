package by.tr.web.controller.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import by.tr.web.entity.User;

public class ReviewTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  HashMap<User, String> reviews;

		
	public HashMap<User, String> getReviews() {
		return reviews;
	}


	public void setReviews(HashMap<User, String> reviews) {
		this.reviews = reviews;
	}


	public int doStartTag() throws JspTagException {
		try {
			pageContext.getOut().write("<table style=\"width:100%; border-collapse: collapse;\">");
			for (Entry<User, String> pair : reviews.entrySet()) {
				User user = pair.getKey();
				String review =  pair.getValue();
				int status = user.getStatus();
				String str;
				if(status>0){
					str = "<b><font color=\"green\">" + status + "</font></b>";
				}
				else{
					if(status<0){
					str = "<b><font color=\"red\">" + status + "</font></b>";
					}
					else{
						str = "<b>" + status + "</b>";
					}
				}
				pageContext.getOut().write("<tr style = \"border-bottom: 1px solid black; border-top: 1px solid black;\" >");
				pageContext.getOut().write("<td width=\"15%\" style = \"padding: 15px; text-align: center;\" >");
				pageContext.getOut().write("<b>" + user.getLogin()+"</b></br>");
				pageContext.getOut().write(str);
				pageContext.getOut().write("</td>");
				pageContext.getOut().write("<td>");
				pageContext.getOut().write(review);
				pageContext.getOut().write("</td></tr>");
			}
			pageContext.getOut().write("</table>");
		 
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
