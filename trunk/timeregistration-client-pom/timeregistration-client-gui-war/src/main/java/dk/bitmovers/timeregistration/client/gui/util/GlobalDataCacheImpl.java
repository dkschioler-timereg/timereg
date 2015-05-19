package dk.bitmovers.timeregistration.client.gui.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@Component("globalDataCache")
public class GlobalDataCacheImpl implements GlobalDataCache {

	WorkClockEventProvider workClockEventProvider;

	List<WorkClockEventType> workClockEventTypes;

	@Autowired
	public GlobalDataCacheImpl(@Qualifier("workClockEventProvider") WorkClockEventProvider workClockEventProvider) {
		this.workClockEventProvider = workClockEventProvider;
		loadData();
	}

	@Override
	public List<WorkClockEventType> getWorkClockEventTypes() throws TimeregistrationException {
		return workClockEventTypes;
	}

	@Override
	public void reload() throws TimeregistrationException {
		loadData();
	}

	private void loadData() {
		workClockEventTypes = workClockEventProvider.getWorkClockEventTypes();
	}

}
