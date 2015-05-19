package dk.bitmovers.timeregistration.client.gui.event.handler;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;

public abstract class AbstractTimeregistrationEventHandler implements TimeregistrationEventHandler {

	public AbstractTimeregistrationEventHandler() {

	}

	protected TimeRegistrationSession getTimeregistrationSession() {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		return trSession;
	}
}
