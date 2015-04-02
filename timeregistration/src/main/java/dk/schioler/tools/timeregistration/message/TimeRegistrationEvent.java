package dk.schioler.tools.timeregistration.message;

import java.util.Date;

import dk.schioler.tools.timeregistration.model.Task;

public class TimeRegistrationEvent {

	private final Date timestamp;
	private final Task task;

	public TimeRegistrationEvent(Date timestamp, Task task) {
		super();
		this.timestamp = timestamp;
		this.task = task;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Task getTask() {
		return task;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		TimeRegistrationEvent other = (TimeRegistrationEvent) obj;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeRegistrationEvent [timestamp=" + timestamp + ", task=" + task + "]";
	}

}
