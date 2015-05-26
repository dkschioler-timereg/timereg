package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import dk.bitmovers.timeregistration.client.gui.ViewManager;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;

public class Menu extends CustomComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	HorizontalLayout layout = new HorizontalLayout();

	MenuBar menubar = new MenuBar();

	Navigator navigator;

	public Menu(final Navigator navigator, final List<ViewInfo> items) {
		super();
		// this.menu.setPrimaryStyleName(TimeregStyle.TIMEREG_MENU);
		// addStyleName(TimeregStyle.TIMEREG_MENU);
		//
		for (final ViewInfo menuItemInfo : items) {
			logger.debug("adding itemInfo:{}", menuItemInfo);
			this.menubar.addItem(menuItemInfo.labelKey, new MenuBar.Command() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					navigator.navigateTo(menuItemInfo.navigateTo);
				}
			});
		}

		// addComponent(menubar);
		// addComponent(lbl);
		setCompositionRoot(menubar);
	}



	//
	// public Menu(final Navigator navigator) {
	// super();
	// logger.debug("Menu constructor, {}", this);
	//
	// menubar.addItem("Home", new MenuBar.Command() {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void menuSelected(MenuItem selectedItem) {
	// navigator.navigateTo("");
	// }
	// });
	// menubar.addItem("provider", new MenuBar.Command() {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void menuSelected(MenuItem selectedItem) {
	// navigator.navigateTo(View.PROVIDER.toString());
	// }
	// });
	//
	// menubar.addItem("Registration", new MenuBar.Command() {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void menuSelected(MenuItem selectedItem) {
	// navigator.navigateTo(View.REGISTRATION.toString());
	// }
	// });
	//
	// menubar.addItem("reports", new MenuBar.Command() {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void menuSelected(MenuItem selectedItem) {
	// Notification.show("Reports clicked");
	// }
	// });
	//
	// setCompositionRoot(menubar);
	//
	// }

}
