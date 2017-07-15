package ua.nure.sereda.SummaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter for UTF-8 encoding
 */
public class EncodingFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	private String encoding;

	@Override
	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		
		LOG.debug("Filter starts");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		LOG.trace("Request uri >> " + httpRequest.getRequestURI());
		
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOG.trace("Request encoding = null, set encoding >> " + encoding);
			request.setCharacterEncoding(encoding);
		}
		
		LOG.debug("Filter finished");		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Filter initialization starts");
		encoding = filterConfig.getInitParameter("encoding");
		LOG.trace("Encoding from web.xml >> " + encoding);
		LOG.debug("Filter initialization finished");
	}

}

