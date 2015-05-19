package dk.bitmovers.timeregistration.client.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationNavigatorUI;


@Title("Provider")
public class ProviderView extends AbstractView<ProviderView> {

	ComboBox cmbBox = new ComboBox("Provider");

	private static final long serialVersionUID = 1L;

	public ProviderView(TimeregistrationNavigatorUI trNavigator, Navigator navigator) {
		super(ProviderView.class, trNavigator, navigator);

		//		addComponent(comp);
		//		setComponentAlignment(comp, Alignment.BOTTOM_CENTER);
		//		addComponents();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("ProviderView");
	}

	private void addComponents() {
		cmbBox.addItem("Select 1");

		cmbBox.addItem("select2");
		addComponent(cmbBox);
	}

	@Override
	public String getName() {

		return Views.PROVIDER;
	}

}
