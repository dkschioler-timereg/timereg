package dk.bitmovers.timeregistration.client.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.client.gui.component.Menu;
import dk.bitmovers.timeregistration.client.gui.component.StatusComponent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

public abstract class AbstractView<T> extends VerticalLayout implements TimeRegistrationView, TimeregistrationEventListener {

	protected Logger logger;

	private static final long serialVersionUID = 1L;

	protected final TimeregistrationNavigatorUI timeregistrationNavigatorUI;

	private final DataProvider dataProvider;

	private final Menu menu;

	private final StatusComponent statusComponent = new StatusComponent();

	private final String name;

	protected AbstractView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, final ViewInfo viewInfo, DataProvider dataProvider) {
		super();
		this.timeregistrationNavigatorUI = timeregistrationNavigatorUI;
		this.dataProvider = dataProvider;
		addStyleName(TimeregStyle.TIMEREG_PAGE);

		logger = LoggerFactory.getLogger(this.getClass());

		this.menu = new Menu(timeregistrationNavigatorUI.getNavigator(), this.dataProvider.getMenuItems());
		this.name = viewInfo.labelKey;

		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.addStyleName(TimeregStyle.TIMEREG_HEADER);

		headerLayout.addComponent(menu);
		addComponent(headerLayout);

		statusComponent.setReadOnly(true);

		statusComponent.setReadOnly(true);

		HorizontalLayout row4 = new HorizontalLayout();

		row4.addStyleName(TimeregStyle.TIMEREG_ROW);
		row4.addComponent(statusComponent);

		addComponent(row4);

	}

	public TimeregistrationNavigatorUI getTimeregistrationNavigatorUI() {
		return timeregistrationNavigatorUI;
	}

	public Navigator getNavigator() {
		return this.timeregistrationNavigatorUI.getNavigator();
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public Menu getMenu() {
		return menu;
	}

	@Override
	public String getName() {
		return name;
	}

	protected TimeregistrationEvent handleEventInView(TimeregistrationEvent event){
		throw new TimeregistrationException("Abstract view impl called - not what you want....");
		
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		return true;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		TimeregistrationEvent e = handleEventInView(event);
		statusComponent.setStatus(e.getStatus());
		return "updated status";
	}

}
