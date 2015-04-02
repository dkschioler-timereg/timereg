package dk.schioler.tools.timeregistration;

import java.text.SimpleDateFormat;

public class TimeRegistrationPropertyKeys {

	public final static String SEPARATOR = ";";
	public final static String SIMPLE_DATE_OUTPUTFORMAT = "yyyyMMdd-HHmmss-SSS";
	public final static String CONSOLE_DATE_OUTPUTFORMAT = "HH:mm:ss, yyyy-MM-dd";
	public final static String REPORT_BASIC_OUTPUTFORMAT = "yyyy-MM-dd HH:mm:ss";

	// No threading issues, so ok in this context.
	public static final SimpleDateFormat REPORT_BASIC = new SimpleDateFormat(REPORT_BASIC_OUTPUTFORMAT);
	public static final SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_OUTPUTFORMAT);
	public static final SimpleDateFormat consoleOutFormat = new SimpleDateFormat(CONSOLE_DATE_OUTPUTFORMAT);

	public static final String ERROR_LOG = "error-log";
	public static final String EVENT_LOG = "event-log";
	public static final String STATISTICS_LOG = "statistics-log";

	public static final String KEY_EXPORTFILE = "config.freya-export";
	public static final String KEY_USERCONFIG_FILE = "config.user-projects";

	private TimeRegistrationPropertyKeys() {

	}

}
