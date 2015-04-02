package dk.schioler.tools.timeregistration.report;

import java.util.Date;

public interface Period extends ReportElement {
	public Date getPeriodStart();

	public Date getPeriodEnd();
}
