package dk.schioler.tools.timeregistration.report.daily;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.report.Category;
import dk.schioler.tools.timeregistration.report.Timeslot;
import dk.schioler.tools.timeregistration.report.Visitor;
import dk.schioler.tools.timeregistration.statistics.EventCategoryProvider;

public class TimeslotImpl implements Timeslot {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final DateTime start;
	private final DateTime end;

	private Map<String, Category> categoryMap = new TreeMap<String, Category>();

	private List<Event> events = new ArrayList<Event>();

	private EventCategoryProvider eventCategoryProvider;

	private long summedDuration = 0;

	public TimeslotImpl(DateTime start, DateTime end, EventCategoryProvider eventCategoryProvider) {
		super();
		this.start = start;
		this.end = end;
		this.eventCategoryProvider = eventCategoryProvider;
	}

	@Override
	public boolean addEvent(Event event) {
		logger.debug("addEvent called- slotStart={}, slotEnd={},  event={}", start, end, event);
		boolean added = false;
		if (event.getStart() >= start.getMillis() && event.getStart() < end.getMillis()) {
			String categoryName = eventCategoryProvider.getEventData(event);
			Category category = categoryMap.get(categoryName);
			if (category == null) {
				category = new Category(categoryName);
				categoryMap.put(category.getCategory(), category);
			}
			category.addEvent(event);
			events.add(event);
			summedDuration = summedDuration + event.getDurationInMillis();
			added = true;
		} else {
			logger.debug("skipping event: {}", event.getStart());
		}
		logger.debug("returning:{}", added);
		return added;
	}

	@Override
	public long getSummedDuration() {
		return summedDuration;
	}

	@Override
	public boolean acccept(Visitor visitor) {
		boolean carryOn = visitor.visit(this);
		if (carryOn) {
			Set<Entry<String, Category>> entrySet = categoryMap.entrySet();
			for (Entry<String, Category> entry : entrySet) {
				Category value = entry.getValue();
				if (!value.acccept(visitor)) {
					carryOn = false;
					break;
				}
			}

		}
		return carryOn;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public DateTime getStart() {
		return start;
	}

	@Override
	public DateTime getEnd() {
		return end;
	}

	@Override
	public List<Category> getCategories() {
		Collection<Category> values = this.categoryMap.values();
		return new ArrayList<Category>(values);
	}

	@Override
	public List<Event> getEvents() {
		return this.events;
	}

}
