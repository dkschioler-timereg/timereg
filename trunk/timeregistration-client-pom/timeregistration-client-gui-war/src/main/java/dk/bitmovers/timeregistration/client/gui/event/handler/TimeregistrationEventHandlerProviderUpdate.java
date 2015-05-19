package dk.bitmovers.timeregistration.client.gui.event.handler;

import java.util.List;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.Provider;

public class TimeregistrationEventHandlerProviderUpdate extends AbstractTimeregistrationEventHandler {

	public TimeregistrationEventHandlerProviderUpdate() {

	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		return event instanceof TimeregistrationEventProviderUpdate;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		TimeregistrationEventProviderUpdate trProviderUpdate = (TimeregistrationEventProviderUpdate) event;
		String value = trProviderUpdate.getValue();

		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		List<Provider> providers = trSession.getProviders();

		Provider currentProvider = null;
		for (Provider client : providers) {
			if (client.getName().equals(value)) {
				currentProvider = client;
				break;
			}
		}
		trSession.setCurrentProvider(currentProvider);

		return "Current provider updated: " + currentProvider.getName();

	}

}
