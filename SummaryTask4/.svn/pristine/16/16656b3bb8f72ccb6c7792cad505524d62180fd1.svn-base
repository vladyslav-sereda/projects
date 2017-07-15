package ua.nure.sereda.SummaryTask4.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Simple tag class for  formatting date field into natural format for user interface
 */
public class DateTag extends SimpleTagSupport {

    private StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);

        getJspContext().getOut().write(parseDate(sw.toString()));
    }

    private String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        return localDate.format(formatter);
    }
}
