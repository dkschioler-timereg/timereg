package dk.bitmovers.timeregistration.client.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dk.bitmovers.timeregistration.data.provider.ClientProvider;

public class TimeRegistrationSessionInit implements Filter {

	public TimeRegistrationSessionInit() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException, ServletException {
	/*	HttpServletRequest req = (HttpServletRequest) request;
		TimeregistrationSession trSession = (TimeRegistrationSession) req.getSession().getAttribute(
				ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		if (trSession == null) {
			trSession = new TimeRegistrationSession();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			ClientProvider clientProvider = (ClientProvider) webApplicationContext.getBean("clientProvider");
			ClientProvider providerProvider = (ClientProvider) webApplicationContext.getBean("providerProvider");
			//clientProvider.queryClients(criteria)
		}

		ServletContext servletContext = request.getServletContext();*/

	}

	@Override
	public void init(FilterConfig request) throws ServletException {

	}

}
