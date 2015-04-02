package dk.schioler.tools.timeregistration.report;

import java.util.ArrayList;
import java.util.List;

import dk.schioler.tools.timeregistration.model.Event;

public class Category implements ReportElement {

	private String category;
	long summedDurationInMillis = 0;
	private List<Event> events = new ArrayList<Event>();

	public Category(String category) {
		super();
		this.category = category;
	}

	public boolean addEvent(Event e) {

		boolean added = events.add(e);
		if (added) {
			summedDurationInMillis = summedDurationInMillis + e.getDurationInMillis();
		}
		return added;
	}

	public String getCategory() {
		return category;
	}

	public List<Event> getEvents() {
		return events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [category=" + category + ", events=" + events + "]";
	}

	@Override
	public long getSummedDuration() {
		return this.summedDurationInMillis;
	}

	@Override
	public boolean acccept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}

}
