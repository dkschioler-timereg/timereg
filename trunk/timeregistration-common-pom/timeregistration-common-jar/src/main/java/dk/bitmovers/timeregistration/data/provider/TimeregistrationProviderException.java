package dk.bitmovers.timeregistration.data.provider;

import dk.bitmovers.timeregistration.common.TimeregistrationException;

public class TimeregistrationProviderException extends TimeregistrationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeregistrationProviderException() {

	}

	public TimeregistrationProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public TimeregistrationProviderException(String message, Throwable cause) {
		super(message, cause);

	}

	public TimeregistrationProviderException(String message) {
		super(message);

	}

	public TimeregistrationProviderException(Throwable cause) {
		super(cause);

	}

}
