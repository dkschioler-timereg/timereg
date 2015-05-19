package dk.bitmovers.timeregistration.client.gui.event;

public class TimeregistrationEventProviderUpdate extends TimeregistrationEvent {

	private static final long serialVersionUID = 1L;

	public TimeregistrationEventProviderUpdate(String client) {
		this.value = client;

	}

	private final String value;

	public String getValue() {
		return value;
	}
}
