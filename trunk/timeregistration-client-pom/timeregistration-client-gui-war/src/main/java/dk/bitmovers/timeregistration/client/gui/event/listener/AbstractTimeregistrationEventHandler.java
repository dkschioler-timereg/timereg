package dk.bitmovers.timeregistration.client.gui.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;

public abstract class AbstractTimeregistrationEventHandler implements TimeregistrationEventListener {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractTimeregistrationEventHandler() {

	}

	protected TimeRegistrationSession getTimeregistrationSession() {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		return trSession;
	}
}
