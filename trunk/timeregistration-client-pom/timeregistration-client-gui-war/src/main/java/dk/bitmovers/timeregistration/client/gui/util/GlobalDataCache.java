package dk.bitmovers.timeregistration.client.gui.util;

import java.util.List;

import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

public interface GlobalDataCache {
	public List<WorkClockEventType> getWorkClockEventTypes() throws TimeregistrationException;
	public void reload() throws TimeregistrationException;
}