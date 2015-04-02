package dk.schioler.tools.timeregistration.statistics;

import dk.schioler.tools.timeregistration.model.Event;

public interface EventCategoryProvider {
	public String getEventData(Event e);
}
