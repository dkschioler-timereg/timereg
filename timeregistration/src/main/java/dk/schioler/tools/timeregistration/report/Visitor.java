package dk.schioler.tools.timeregistration.report;

public interface Visitor {
	public boolean visit(ReportElement report);
}
