package dk.bitmovers.timeregistration.client.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.client.gui.vaadin.components.Menu;

public abstract class AbstractView<T> extends CssLayout implements TimeRegistrationView {
	protected Logger logger;

	private static final long serialVersionUID = 1L;

	protected TimeregistrationNavigatorUI timeregistrationNavigatorUI;

	protected Navigator navigator;

	protected Menu menu;

	protected AbstractView(Class<T> clazz, TimeregistrationNavigatorUI timeregistrationNavigatorUI, Navigator navigator) {
		super();
		this.timeregistrationNavigatorUI = timeregistrationNavigatorUI;
		logger = LoggerFactory.getLogger(clazz);
		addStyleName(TimeregStyle.TIMEREG_PAGE);

		CssLayout headerLayout = new CssLayout();
		headerLayout.addStyleName(TimeregStyle.TIMEREG_HEADER);

		addComponent(headerLayout);
		CssLayout menuLayout = new CssLayout();
		menuLayout.addStyleName(TimeregStyle.TIMEREG_MENU);

		this.menu = new Menu(navigator);
		this.menu.addStyleName(TimeregStyle.TIMEREG_MENU);
		this.menu.setPrimaryStyleName(TimeregStyle.TIMEREG_MENU);

		menuLayout.addComponent(menu);
		headerLayout.addComponent(menuLayout);

	}

	public TimeregistrationNavigatorUI getTimeregistrationNavigatorUI() {
		return timeregistrationNavigatorUI;
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public Menu getMenu() {
		return menu;
	}
	
	

}
