package dk.bitmovers.timeregistration.client.gui.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@Component("transactionalWorkClockProvider")
public class WorkClockEventProviderTransactionalImpl extends AbstractTransactionWrapper implements WorkClockEventProvider {


	@Autowired
	WorkClockEventProvider workClockEventProvider;

	public WorkClockEventProviderTransactionalImpl() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkClockEventType> getWorkClockEventTypes() throws TimeregistrationProviderException {
	
		logger.debug("getWorkClockEventTypes!");
		Executor rv = new Executor() {
			@Override
			public Object doIt() {
				return workClockEventProvider.getWorkClockEventTypes();
			}
		};
		logger.debug("getWorkClockEventTypes!preRun");

		return (List<WorkClockEventType>) run(rv);
	}

	@Override
	public Integer saveWorkClockEvent(final WorkClockEvent event) throws TimeregistrationProviderException {

		Executor rv = new Executor() {
			@Override
			public Object doIt() {
				return workClockEventProvider.saveWorkClockEvent(event);
			}
		};

		return (Integer) run(rv);
	}

	@Override
	public WorkClockEvent deleteWorkClockEvent(WorkClockEvent event) throws TimeregistrationProviderException {

		return null;
	}




}
