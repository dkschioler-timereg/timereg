package dk.bitmovers.timeregistration.client.view;

import java.util.List;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.client.gui.component.ClientComponent;
import dk.bitmovers.timeregistration.client.gui.component.ClientProjectComponent;
import dk.bitmovers.timeregistration.client.gui.component.ProviderAccountComponent;
import dk.bitmovers.timeregistration.client.gui.component.ProviderComponent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.listener.TimeregistrationEventClientSetHandler;
import dk.bitmovers.timeregistration.client.gui.event.listener.TREventHandlersetCurrentProviderInTRSession;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.model.Client;

@Title("Provider")
public class RegistrationView extends AbstractView<RegistrationView> implements TimeregistrationEventListener {

	ClientComponent clientComponent = new ClientComponent(TimeregStyle.TIMEREG_COMPONENT);
	ClientProjectComponent clientProjectComponent = new ClientProjectComponent("this");

	ProviderComponent providers = new ProviderComponent("this");
	ProviderAccountComponent providerAccount = new ProviderAccountComponent("this");

	private static final long serialVersionUID = 1L;

	
	public RegistrationView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, ViewInfo viewInfo, DataProvider dataProvider) {
		super(timeregistrationNavigatorUI, viewInfo, dataProvider);
			
//		eventHandlers.add(new TREventHandlerClientSetCurrentClientInTRSession());
//		eventHandlers.add(new TREventHandlersetCurrentProviderInTRSession());
		
		HorizontalLayout row1 = new HorizontalLayout();

		row1.addStyleName(TimeregStyle.TIMEREG_ROW);

		row1.addComponent(clientComponent);
		row1.addComponent(clientProjectComponent);

		HorizontalLayout row2 = new HorizontalLayout();
		row2.addStyleName(TimeregStyle.TIMEREG_ROW);
		row2.addComponent(providers);
		row2.addComponent(providerAccount);

		addComponent(row1);
		addComponent(row2);

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
//
//			clientComponent.update(trSession);
//			clientProjectComponent.update(trSession);
//			providers.update(trSession);
//			providerAccount.update(trSession);

			isInit = true;
		}
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		// TODO Auto-generated method stub
		return false;
	}

}
