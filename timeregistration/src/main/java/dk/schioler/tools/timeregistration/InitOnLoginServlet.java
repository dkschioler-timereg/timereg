package dk.schioler.tools.timeregistration;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitOnLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private WebApplicationContext ctx = null; 

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		super.doGet(req, resp);
		doRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		super.doPost(req, resp);
		doRequest(req, resp);
	}

	protected void doRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken upa = (UsernamePasswordAuthenticationToken) authentication;
			Object principal = upa.getPrincipal();
			if (principal instanceof String) {
				String login = (String) principal;
				
			}
		}

	}
}
