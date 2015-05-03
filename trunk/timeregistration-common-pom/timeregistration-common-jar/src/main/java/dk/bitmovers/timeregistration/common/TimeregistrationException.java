package dk.bitmovers.timeregistration.common;

public class TimeregistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TimeregistrationException() {
		super();
	
	}

	public TimeregistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public TimeregistrationException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public TimeregistrationException(String message) {
		super(message);
	
	}

	public TimeregistrationException(Throwable cause) {
		super(cause);
	
	}

	
}
