package dk.bitmovers.timeregistration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.RedirectStrategy;

public class TimeregistrationTimeStrategy implements RedirectStrategy {

	public TimeregistrationTimeStrategy() {

	}

	@Override
	public void sendRedirect(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString)
			throws IOException {
		try {
			paramHttpServletRequest.getRequestDispatcher(paramString).forward(paramHttpServletRequest, paramHttpServletResponse);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
