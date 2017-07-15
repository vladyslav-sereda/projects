package ua.nure.sereda.Photostudio.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Simple tag class for formatting date-time field into natural format for user interface
 *
 */
public class DateTimeFormatTag extends SimpleTagSupport {

	private StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		
		getJspContext().getOut().write(parseDateTime(sw.toString()));
	}
	
	private String parseDateTime(String dateTime){
		
		return dateTime.split("T")[0]+" "+dateTime.split("T")[1];	
	}
}
