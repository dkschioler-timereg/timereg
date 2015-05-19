package dk.bitmovers.timeregistration.client.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.event.handler.TimeregistrationEventHandler;
import dk.bitmovers.timeregistration.client.gui.event.handler.TimeregistrationEventHandlerClientUpdate;
import dk.bitmovers.timeregistration.client.gui.event.handler.TimeregistrationEventHandlerClockStart;
import dk.bitmovers.timeregistration.client.gui.event.handler.TimeregistrationEventHandlerClockStop;
import dk.bitmovers.timeregistration.client.gui.event.handler.TimeregistrationEventHandlerProviderUpdate;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.client.gui.vaadin.components.ClientComponent;
import dk.bitmovers.timeregistration.client.gui.vaadin.components.ProviderComponent;
import dk.bitmovers.timeregistration.client.gui.vaadin.components.StatusComponent;
import dk.bitmovers.timeregistration.client.gui.vaadin.components.WorkClockComponent;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.Client;

@Title("View")
public class IndexView extends AbstractView<IndexView> implements TimeregistrationEventListener {

	private static final long serialVersionUID = 1L;

	WorkClockComponent workClockComponent = new WorkClockComponent(this);
	ClientComponent clientComponent = new ClientComponent(this, TimeregStyle.TIMEREG_COMPONENT);
	StatusComponent statusComponent = new StatusComponent();
	ProviderComponent providers = new ProviderComponent(this);

	List<TimeregistrationEventHandler> eventHandlers = new ArrayList<TimeregistrationEventHandler>();

	public IndexView(TimeregistrationNavigatorUI trNavigator, Navigator navigator) {
		super(IndexView.class, trNavigator, navigator);

		eventHandlers.add(new TimeregistrationEventHandlerClientUpdate());
		eventHandlers.add(new TimeregistrationEventHandlerProviderUpdate());
		eventHandlers.add(new TimeregistrationEventHandlerClockStart(trNavigator.getWorkClockEventProvider()));
		eventHandlers.add(new TimeregistrationEventHandlerClockStop(trNavigator.getWorkClockEventProvider()));

		CssLayout row1 = new CssLayout();

		row1.addStyleName(TimeregStyle.TIMEREG_ROW);
		row1.addComponent(clientComponent);

		addComponent(row1);

		CssLayout row2 = new CssLayout();

		row2.addStyleName(TimeregStyle.TIMEREG_ROW);
		row2.addComponent(providers);

		addComponent(row2);

		CssLayout row3 = new CssLayout();

		row3.addStyleName(TimeregStyle.TIMEREG_ROW);
		row3.addComponent(workClockComponent);

		addComponent(row3);

		statusComponent.setReadOnly(true);

		CssLayout row4 = new CssLayout();

		row4.addStyleName(TimeregStyle.TIMEREG_ROW);
		row4.addComponent(statusComponent);

		addComponent(row4);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("IndexView calling");
		initUserSession();

	}

	boolean isInit = false;

	private void initUserSession() {
		logger.debug("init. isInit={}", isInit);
		if (!isInit) {

			WrappedSession session = VaadinSession.getCurrent().getSession();
			TimeRegistrationSession trSession = (TimeRegistrationSession) session
					.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);

			List<Client> clients2 = trSession.getClients();
			logger.debug("clients={}", clients2);
			clientComponent.updateContent(trSession);
			providers.updateContent(trSession);

			isInit = true;
		}
	}

	@Override
	public String getName() {
		return Views.INDEX;
	}

	@Override
	public Object handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		logger.debug("handleEvent:" + event);
		List<String> statusLines = new ArrayList<String>();

		for (TimeregistrationEventHandler handler : this.eventHandlers) {
			if (handler.supports(event)) {
				logger.debug("EVENT HANDLED BY." + handler);
				String status = handler.handleEvent(event);
				statusLines.add(status);
			}
		}

		statusComponent.updateStatus(statusLines);
		return null;
	}

}
