package dk.bitmovers.timeregistration.client.gui.event;


public class TimeregistrationEventClientUpdate extends TimeregistrationEvent {

	private static final long serialVersionUID = 1L;

	private final String value;

	public TimeregistrationEventClientUpdate(String client) {
		this.value = client;
	}

	public String getValue() {
		return value;
	}
	

}
