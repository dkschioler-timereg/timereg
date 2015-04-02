package dk.schioler.tools.timeregistration.message;

import java.util.Map;

import org.springframework.messaging.support.GenericMessage;

public class TimeRegistrationEventMessage extends GenericMessage<TimeRegistrationEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeRegistrationEventMessage(TimeRegistrationEvent payload) {
		super(payload);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TimeRegistrationEventMessage(TimeRegistrationEvent payload, Map headers) {
		super(payload, headers);
	}

}
