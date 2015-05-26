package dk.bitmovers.timeregistration.client.gui;

import java.io.Serializable;

public abstract class TimeregistrationEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	private final String value;

	private final String caption;

	public TimeregistrationEvent(String value, String caption) {
		this.caption = caption;
		this.value = value;
	}

	public TimeregistrationEvent() {
		this("","");
	}
	
	public String getValue() {
		return value;
	}



	public String getCaption() {
		return caption;
	}



	public void updateStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
