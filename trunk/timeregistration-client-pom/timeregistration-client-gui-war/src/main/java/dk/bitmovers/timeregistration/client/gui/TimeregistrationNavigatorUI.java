package dk.bitmovers.timeregistration.client.gui;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.ConnectorTracker;
import com.vaadin.ui.UI;

import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.client.view.IndexView;
import dk.bitmovers.timeregistration.client.view.ProviderView;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationView;
import dk.bitmovers.timeregistration.client.view.Views;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.WorkClockEvent;

@Theme("timereg")
@Title("Timeregistration")
public class TimeregistrationNavigatorUI extends UI {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	Navigator navigator;

	private static final long serialVersionUID = 1L;

	WebApplicationContext webApplicationContext;

	WorkClockEventProvider workClockEventProvider;

	TimeRegistrationView indexView;

	private ConnectorTracker tracker;

	TimeRegistrationView providerView;

	public TimeregistrationNavigatorUI() {

	}

	public TimeregistrationNavigatorUI(Component content) {
		super(content);
	}

	@SuppressWarnings("serial")
	@Override
	public ConnectorTracker getConnectorTracker() {
		if (this.tracker == null) {
			this.tracker = new ConnectorTracker(this) {

				@Override
				public void registerConnector(ClientConnector connector) {
					try {
						super.registerConnector(connector);
					} catch (RuntimeException e) {
						logger.error("Failed connector: {0}", connector.getClass().getSimpleName());
						throw e;
					}
				}

			};
		}

		return tracker;
	}

	@Override
	protected void init(VaadinRequest request) {
		logger.debug("*******************************");
		ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		workClockEventProvider = webApplicationContext.getBean("workClockEventProvider", WorkClockEventProvider.class);
		navigator = new Navigator(this, this);

		indexView = new IndexView(this, navigator);
		providerView = new ProviderView(this, navigator);

		navigator.addView("", indexView);
		navigator.addView(Views.PROVIDER.toString(), providerView);

		this.addStyleName(TimeregStyle.TIMEREG_UI);
	}

	public WorkClockEventProvider getWorkClockEventProvider() {
		return workClockEventProvider;
	}

	
//	public Integer persistWorkClockEvent(WorkClockEvent workClockEvent) {
//
//		Integer saveWorkClockEvent = workClockEventProvider.saveWorkClockEvent(workClockEvent);
//		return saveWorkClockEvent;
//	}

}
