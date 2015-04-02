package dk.schioler.tools.timeregistration.report;

import dk.schioler.tools.timeregistration.model.Event;

public interface ReportElement {

	public String getName();

	public boolean addEvent(Event event);

	public long getSummedDuration();

	public boolean acccept(Visitor visitor);

}
