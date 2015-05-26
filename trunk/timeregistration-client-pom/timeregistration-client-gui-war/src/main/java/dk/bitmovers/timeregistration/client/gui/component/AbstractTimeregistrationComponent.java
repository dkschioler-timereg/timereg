package dk.bitmovers.timeregistration.client.gui.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.CustomComponent;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventEmitter;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;

public abstract class AbstractTimeregistrationComponent extends CustomComponent implements TimeregistrationEventEmitter {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final List<TimeregistrationEventListener> eventListeners = new ArrayList<TimeregistrationEventListener>();

	public AbstractTimeregistrationComponent(String style) {
		super();
	}

	public void addTimeregistrationEventListener(TimeregistrationEventListener listener) {
		eventListeners.add(listener);
	}

	protected void notify(TimeregistrationEvent event) {
		for (TimeregistrationEventListener timeregistrationEventListener : eventListeners) {
			if (timeregistrationEventListener.supports(event)) {
				timeregistrationEventListener.handleEvent(event);
			}
		}
	}

	public void initContent(DataProvider dataProvider) {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		updateContent(session, trSession, dataProvider);
	}

	public abstract void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider);
}
