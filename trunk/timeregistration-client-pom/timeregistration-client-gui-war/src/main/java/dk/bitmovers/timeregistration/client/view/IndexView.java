package dk.bitmovers.timeregistration.client.view;

import java.util.List;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.client.gui.component.ClientComponent;
import dk.bitmovers.timeregistration.client.gui.component.ProviderComponent;
import dk.bitmovers.timeregistration.client.gui.component.WorkClockComponent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.model.Client;

@Title("View")
public class IndexView extends AbstractView<IndexView> {

	private static final long serialVersionUID = 1L;

	WorkClockComponent workClockComponent = new WorkClockComponent("");
	ClientComponent clientComponent = new ClientComponent(TimeregStyle.TIMEREG_COMPONENT);

	ProviderComponent providers = new ProviderComponent(TimeregStyle.TIMEREG_COMPONENT);

	public IndexView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, ViewInfo viewInfo, DataProvider dataProvider) {
		super(timeregistrationNavigatorUI, viewInfo, dataProvider);

		// Adding components:
		CssLayout row1 = new CssLayout();

		row1.addStyleName(TimeregStyle.TIMEREG_ROW);
		clientComponent.addTimeregistrationEventListener(this);
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

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show(getName());
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
			clientComponent.updateContent(session, trSession, getDataProvider());
			// providers.doInit(session, trSession, dataProvider);

			isInit = true;
		}
	}

	protected TimeregistrationEvent handleEventInView(TimeregistrationEvent event) {
		TimeregistrationEvent retval = null;
		WrappedSession session = VaadinSession.getCurrent().getSession();
		TimeRegistrationSession trSession = (TimeRegistrationSession) session.getAttribute(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);

		if (event instanceof TimeregistrationEventClientUpdate) {
			TimeregistrationEventClientUpdate e = (TimeregistrationEventClientUpdate) event;
			String value = e.getValue();
			String caption = e.getCaption();
			// TODO fix lookup from db..
			Client currentClient = null;
			List<Client> clients2 = trSession.getClients();
			for (Client client : clients2) {
				if (String.valueOf(client.getId()).equals(value)) {
					currentClient = client;
					break;
				}
			}

			trSession.setCurrentClient(currentClient);
			e.updateStatus("Client selected=" + caption + ", id=" + value + ", has been set in session");
			retval = e;
		}

		return retval;

	}
}
