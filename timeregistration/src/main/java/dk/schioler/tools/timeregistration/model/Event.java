package dk.schioler.tools.timeregistration.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import dk.schioler.tools.timeregistration.TimeRegistrationException;

public class Event implements Comparable<Event> {

	private final Task task;
	private final long start;
	private long end = -1;

	public Event(Task task, long start) {
		super();
		if (task == null) {
			throw new TimeRegistrationException("Task can not be null");
		}
		if (start < 1) {
			throw new TimeRegistrationException("start must be larger than 0");
		}
		this.task = task;
		task.addEvent(this);
		this.start = start;
	}

	@Override
	public int compareTo(Event arg0) {
		long span = this.start - arg0.start;
		return span > 0 ? 1 : -1;
	}

	public long getDurationInMillis() {
		if (!this.task.equals(Util.TASK_STOP)) {
			return end - start;
		} else {			
			return -1;
//			throw new TimeRegistrationException("End has not been set on this event");
		}
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public Task getTask() {
		return task;
	}

	public long getStart() {
		return start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (end ^ (end >>> 32));
		result = prime * result + (int) (start ^ (start >>> 32));
		result = prime * result + ((task == null) ? 0 : task.hashCode());
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
		Event other = (Event) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	@Override
	public String toString() {
		return "Event [start=" + sdf.format(new Date(start)) + ", end=" + sdf.format(new Date(end)) + ", task=" + task.getTask() + "]";
	}

}
