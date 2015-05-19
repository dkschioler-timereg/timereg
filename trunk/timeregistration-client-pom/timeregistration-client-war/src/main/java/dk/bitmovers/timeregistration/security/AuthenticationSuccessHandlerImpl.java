package dk.bitmovers.timeregistration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.model.User;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public AuthenticationSuccessHandlerImpl() {

	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException,
			ServletException {
		logger.debug("auth={}", auth);
		WebAuthenticationDetails webAuth = (WebAuthenticationDetails) auth.getDetails();
		UserDetailsImpl ud = (UserDetailsImpl) auth.getPrincipal();
		User user = ud.getUser();
		TimeRegistrationSession ses = new TimeRegistrationSession(user);
		req.getSession().setAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION, ses);
		logger.debug("detatils={}", webAuth);
		resp.sendRedirect("/welcome.do");
	}
}
