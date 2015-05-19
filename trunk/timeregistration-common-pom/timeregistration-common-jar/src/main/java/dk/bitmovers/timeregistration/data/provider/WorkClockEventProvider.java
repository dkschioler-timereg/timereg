package dk.bitmovers.timeregistration.data.provider;

import java.util.List;

import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

public interface WorkClockEventProvider {
	public List<WorkClockEventType> getWorkClockEventTypes() throws TimeregistrationProviderException;
	public Integer saveWorkClockEvent(WorkClockEvent event) throws TimeregistrationProviderException;
	public WorkClockEvent deleteWorkClockEvent(WorkClockEvent event) throws TimeregistrationProviderException;
}
