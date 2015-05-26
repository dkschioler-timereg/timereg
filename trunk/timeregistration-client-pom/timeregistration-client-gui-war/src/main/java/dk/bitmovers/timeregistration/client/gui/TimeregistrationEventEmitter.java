package dk.bitmovers.timeregistration.client.gui;


public interface TimeregistrationEventEmitter {
	public void addTimeregistrationEventListener(TimeregistrationEventListener listener);
}
