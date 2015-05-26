package dk.bitmovers.timeregistration.client.gui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.common.TimeregistrationException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;

@Theme("timereg")
@Title("Timeregistration")
public class TimeregistrationNavigatorUI extends UI {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	Navigator navigator;

	private static final long serialVersionUID = 1L;

	WebApplicationContext webApplicationContext;

	// WorkClockEventProvider workClockEventProvider;

	DataProvider dataProvider;

	// TimeRegistrationView indexView;

	// private ConnectorTracker tracker;

	// TimeRegistrationView providerView;
	// TimeRegistrationView registrationView;

	public TimeregistrationNavigatorUI() {

	}

	public TimeregistrationNavigatorUI(Component content) {
		super(content);
	}

	// @SuppressWarnings("serial")
	// @Override
	// public ConnectorTracker getConnectorTracker() {
	// if (this.tracker == null) {
	// this.tracker = new ConnectorTracker(this) {
	//
	// @Override
	// public void registerConnector(ClientConnector connector) {
	// try {
	// super.registerConnector(connector);
	// } catch (RuntimeException e) {
	// logger.error("Failed connector: {0}",
	// connector.getClass().getSimpleName());
	// throw e;
	// }
	// }
	//
	// };
	// }
	//
	// return tracker;
	// }

	@Override
	protected void init(VaadinRequest request) {
		logger.debug("*******************************");
		ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		dataProvider = webApplicationContext.getBean("dataProvider", DataProvider.class);

		navigator = new Navigator(this, this);

		for (ViewManager.ViewInfo info : ViewManager.VIEWS) {
			Constructor<?> cTor = null;
			Constructor<?>[] declaredConstructors = info.implementation.getDeclaredConstructors();
			for (Constructor<?> constructor : declaredConstructors) {
				Class<?>[] parameterTypes = constructor.getParameterTypes();
				if (parameterTypes.length == 3) {
					cTor = constructor;
					break;
				}
			}
			if (cTor == null) {
				throw new TimeregistrationException("Did not find proper constructor for view:" + info);
			}
			logger.debug("Found constructor for view:{}", info);

			com.vaadin.navigator.View viewInstance;
			try {

				viewInstance = (com.vaadin.navigator.View) cTor.newInstance(this, info, dataProvider);
				navigator.addView(info.navigateTo, viewInstance);
				logger.debug(" VIEW VIEW VIEW ------------------- added new menu item:{}", viewInstance);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// indexView = new IndexView(this, navigator);
		// providerView = new ProviderView(this, navigator);
		// providerView = new RegistrationView(this, navigator);
		//
		// navigator.addView("", indexView);
		// navigator.addView(View.PROVIDER.toString(), providerView);
		// navigator.addView(View.REGISTRATION.toString(), providerView);

		this.addStyleName(TimeregStyle.TIMEREG_UI);
		logger.debug("*******************************");
	}



	public Navigator getNavigator() {
		return navigator;
	}

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	// public Integer persistWorkClockEvent(WorkClockEvent workClockEvent) {
	//
	// Integer saveWorkClockEvent =
	// workClockEventProvider.saveWorkClockEvent(workClockEvent);
	// return saveWorkClockEvent;
	// }

}
