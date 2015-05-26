package dk.bitmovers.timeregistration.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import dk.bitmovers.timeregistration.client.gui.util.GlobalDataCache;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.SearchCriteriaImpl;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	GlobalDataCache globalData;

	@Autowired
	ClientProvider clientProvider;

	@Autowired
	ProviderProvider providerProvider;

	public AuthenticationSuccessHandlerImpl(String defaultTargetUrl) {
		super(defaultTargetUrl);
	}

	public AuthenticationSuccessHandlerImpl() {

	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException,
			ServletException {
		//		logger.debug("auth={}", auth); 
//		WebAuthenticationDetails webAuth = (WebAuthenticationDetails) auth.getDetails();
		UserDetailsImpl ud = (UserDetailsImpl) auth.getPrincipal();
		User user = ud.getUser();
		TimeRegistrationSession ses = new TimeRegistrationSession(user);

		List<WorkClockEventType> workClockEventTypes = globalData.getWorkClockEventTypes();

		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(user.getId()));

		List<Client> clients = clientProvider.queryClients(sc);
		logger.debug("clients={}", clients);
		List<Provider> providers = providerProvider.queryProviders(sc);
		logger.debug("providers={}", providers);
		ses.setClients(clients);
		ses.setProviders(providers);
		ses.setWorkClockEventTypes(workClockEventTypes);

		req.getSession().setAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION, ses);
		logger.debug("timeregSession={}", ses);
		super.onAuthenticationSuccess(req, resp, auth);
	}
}
