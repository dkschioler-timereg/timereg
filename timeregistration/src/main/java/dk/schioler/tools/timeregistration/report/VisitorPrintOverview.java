package dk.schioler.tools.timeregistration.report;

import java.io.PrintStream;

import dk.schioler.tools.timeregistration.TimeRegistrationPropertyKeys;

public class VisitorPrintOverview implements Visitor {

	private PrintStream printStream;

	public VisitorPrintOverview(PrintStream printStream) {
		super();
		this.printStream = printStream;
	}

	@Override
	public boolean visit(ReportElement report) {

		if (report instanceof Category) {
			Category cat = (Category) report;
			String c = cat.getCategory();
			int length = Math.min(60, c.length());
			printStream.format("  %60s %10d %n", c.substring(0, length), cat.getSummedDuration());

		} else if (report instanceof Period) {
			Period p = (Period) report;
			printStream.format("%nPeriod:    [%-20s to %-20s]: sum                 %15d %n", TimeRegistrationPropertyKeys.REPORT_BASIC.format(p.getPeriodStart()),
					TimeRegistrationPropertyKeys.REPORT_BASIC.format(p.getPeriodEnd()), p.getSummedDuration());
		} else if (report instanceof Timeslot) {
			Timeslot ts = (Timeslot) report;
			printStream.format(" TimeSlot: [%-20s to %-20s]: sum        %15d %n", TimeRegistrationPropertyKeys.REPORT_BASIC.format(ts.getStart().toDate()),
			      TimeRegistrationPropertyKeys.REPORT_BASIC.format(ts.getEnd().toDate()), ts.getSummedDuration());
		}
		return true;

	}
}
