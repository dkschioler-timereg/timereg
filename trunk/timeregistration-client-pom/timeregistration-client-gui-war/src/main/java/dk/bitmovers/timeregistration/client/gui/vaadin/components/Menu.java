package dk.bitmovers.timeregistration.client.gui.vaadin.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;

import dk.bitmovers.timeregistration.client.view.Views;

public class Menu extends CustomComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	MenuBar menubar = new MenuBar();

	Navigator navigator;

	public Menu(final Navigator navigator) {
		super();
		logger.debug("Menu constructor, {}", this);
		
//		verticalLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

		menubar.addItem("home", new MenuBar.Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				navigator.navigateTo("");
			}
		});
		menubar.addItem("provider", new MenuBar.Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				navigator.navigateTo(Views.PROVIDER.toString());
			}
		});

		menubar.addItem("client", new MenuBar.Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("Client clicked");
			}
		});

		menubar.addItem("reports", new MenuBar.Command() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show("Reports clicked");
			}
		});

//		layout.addComponent(menubar);

//		

//		Panel panel = new Panel("Menu");

//		panel.setContent(layout);
//
//		panel.getContent().setSizeUndefined();
//		panel.setSizeUndefined();
//		setSizeUndefined();
//		panel.setWidth("500");

		setCompositionRoot(menubar);
//		logger.debug("Menu constructor");
	}

}
