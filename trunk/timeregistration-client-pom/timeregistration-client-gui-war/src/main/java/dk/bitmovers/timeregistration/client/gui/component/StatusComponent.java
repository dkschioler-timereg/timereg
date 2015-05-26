package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;

public class StatusComponent extends CustomComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	GridLayout layout = new GridLayout(2, 2);

	protected final Label eventLabel;
	protected final TextField eventField;
	protected final Label statusLabel;
	// protected final TextArea textArea;
	protected final TextField statusField;

	public StatusComponent() {

		eventLabel = new Label("LastEvent:");
		eventLabel.setWidth(String.valueOf("LastEvent".length()) + "rem");
		eventField = new TextField();
		eventField.addStyleName("tr-status");

		statusLabel = new Label("Status:");
		statusLabel.setWidth(String.valueOf("Status".length()) + "rem");

		logger.debug("constructor, {}", this);
		statusField = new TextField();
		statusField.addStyleName("tr-status");

		layout.addComponent(statusLabel);
		layout.addComponent(statusField);
		layout.addComponent(eventLabel);
		layout.addComponent(eventField);

		setCompositionRoot(layout);

	}

	public void setStatus(String status) {
		this.eventField.setValue(status);
	}

	public void updateStatus(List<String> lines) {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		this.statusField.setValue(trSession.toString());

		String status = "";
		for (String string : lines) {
			status = status + string + ", ";
		}
		int length = status.length();
		eventField.setWidth(String.valueOf(length) + "rem");
		this.eventField.setValue(status);

	}
}
