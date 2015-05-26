package dk.bitmovers.timeregistration.client.gui.event;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;

public class TimeregistrationEventContanierUpdate extends TimeregistrationEvent {

	private static final long serialVersionUID = 1L;

	public TimeregistrationEventContanierUpdate(String client) {
		this.value = client;

	}

	private final String value;

	public String getValue() {
		return value;
	}
}
