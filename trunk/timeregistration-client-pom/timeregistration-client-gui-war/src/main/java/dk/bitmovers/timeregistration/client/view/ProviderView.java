package dk.bitmovers.timeregistration.client.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

@Title("Provider")
public class ProviderView extends AbstractView<ProviderView> {

	ComboBox cmbBox = new ComboBox("Provider");

	private static final long serialVersionUID = 1L;

	public ProviderView(TimeregistrationNavigatorUI timeregistrationNavigatorUI, ViewInfo viewInfo, DataProvider dataProvider) {
		super(timeregistrationNavigatorUI, viewInfo, dataProvider);
		// TODO Auto-generated constructor stub
	}

//	public ProviderView(TimeregistrationNavigatorUI trNavigator, final ViewInfo viewInfo, final List<ViewInfo> items) {
//		super(trNavigator, viewInfo, items);
//		 addComponent(comp);
		// setComponentAlignment(comp, Alignment.BOTTOM_CENTER);
		// addComponents();

//	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show(getName());
	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		// TODO Auto-generated method stub
		return false;
	}

}
