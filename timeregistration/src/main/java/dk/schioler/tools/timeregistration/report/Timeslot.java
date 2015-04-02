package dk.schioler.tools.timeregistration.report;

import java.util.List;

import org.joda.time.DateTime;

import dk.schioler.tools.timeregistration.model.Event;

public interface Timeslot extends ReportElement {
	public DateTime getStart();

	public DateTime getEnd();

	public List<Category> getCategories();

	public List<Event> getEvents();
}
