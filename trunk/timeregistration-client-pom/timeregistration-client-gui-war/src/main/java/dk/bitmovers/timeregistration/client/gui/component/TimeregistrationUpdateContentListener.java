package dk.bitmovers.timeregistration.client.gui.component;

import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;


public interface TimeregistrationUpdateContentListener {
	public abstract void update(TimeRegistrationSession trSession);
}
