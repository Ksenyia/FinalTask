package by.tr.web.controller;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class StatusTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;
	
	public double getRating() {
		return status;
	}

	public void setRating(int status) {
		this.status = status;
	}

	@Override
	public int doStartTag() throws JspException {
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
			try{
				JspWriter out = pageContext.getOut();
				out.write(str);
	
			}catch(IOException e){
				throw new JspException(e.getMessage());
			}
		return SKIP_BODY;
	}

}
