package dk.bitmovers.timeregistration.client.gui.event.listener;

import java.util.List;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.Client;

public class TimeregistrationEventClientSetHandler extends AbstractTimeregistrationEventHandler {

	public TimeregistrationEventClientSetHandler() {

	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {

		return event instanceof TimeregistrationEventClientUpdate;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		TimeregistrationEventClientUpdate trClientUpdate = (TimeregistrationEventClientUpdate) event;
		String value = trClientUpdate.getValue();

		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		List<Client> clients = trSession.getClients();

		Client currentClient = null;
		for (Client client : clients) {
			int parseInt = -1;
			try {
				parseInt = Integer.parseInt(value);
				if (client.getId() == parseInt) {
					currentClient = client;
					break;
				}
			} catch (NumberFormatException nfe) {
				logger.error("Caught:" + nfe.getMessage(), nfe);
			}
		}
		
		trSession.setCurrentClient(currentClient);
		return "Current client updated: " + currentClient.getName();

	}

}
