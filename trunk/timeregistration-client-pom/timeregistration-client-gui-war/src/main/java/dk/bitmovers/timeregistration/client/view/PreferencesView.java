package dk.bitmovers.timeregistration.client.view;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

public class PreferencesView extends AbstractView<PreferencesView> {

	ComboBox cmbBox = new ComboBox("Provider");

	private static final long serialVersionUID = 1L;

	// public PreferencesView(TimeregistrationNavigatorUI trNavigator, final
	// ViewInfo viewInfo, final List<ViewInfo> items) {
	// super(trNavigator, viewInfo, items);
	// // addComponent(comp);
	// // setComponentAlignment(comp, Alignment.BOTTOM_CENTER);
	// // addComponents();
	//
	// }

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show(getName());
	}

	public PreferencesView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, ViewInfo viewInfo, DataProvider dataProvider) {
		super(timeregistrationNavigatorUI, viewInfo, dataProvider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		// TODO Auto-generated method stub
		return false;
	}

}
