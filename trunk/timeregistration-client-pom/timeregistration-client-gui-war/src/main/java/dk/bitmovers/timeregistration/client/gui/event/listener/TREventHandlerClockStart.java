package dk.bitmovers.timeregistration.client.gui.event.listener;

import java.util.List;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStart;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

public class TREventHandlerClockStart extends AbstractTimeregistrationEventHandler {

	WorkClockEventProvider workClockEventProvider;

	public TREventHandlerClockStart(WorkClockEventProvider workClockEventProvider) {
		this.workClockEventProvider = workClockEventProvider;
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {

		return event instanceof TimeregistrationEventWorkClockStart;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		TimeregistrationEventWorkClockStart startEvent = (TimeregistrationEventWorkClockStart) event;

		TimeRegistrationSession timeregistrationSession = getTimeregistrationSession();
		Client currentClient = timeregistrationSession.getCurrentClient();
		Provider currentProvider = timeregistrationSession.getCurrentProvider();

		if (currentClient == null || currentProvider == null) {
			throw new TimeregistrationException("CurrentProvider or CurrentCLient is null - please set");
		}
		User user = timeregistrationSession.getUser();

		WorkClockEventType type = null;
		List<WorkClockEventType> workClockEventTypes = timeregistrationSession.getWorkClockEventTypes();
		for (WorkClockEventType workClockEventType : workClockEventTypes) {
			if ("start".equals(workClockEventType.getEventType())) {
				type = workClockEventType;
				break;
			}
		}

		WorkClockEvent wcEvent = new WorkClockEvent();
		wcEvent.setWorkClockEventType(type);
		wcEvent.setCreated(startEvent.getTime());
		wcEvent.setUser(user);
		Integer id = this.workClockEventProvider.saveWorkClockEvent(wcEvent);
		wcEvent.setId(id);
		timeregistrationSession.setCurrentWorkClockEvent(wcEvent);
		return "Started workCLock at " + wcEvent.getCreated();
	}
}
