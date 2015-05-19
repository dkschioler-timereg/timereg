package dk.bitmovers.timeregistration.client.gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

import dk.bitmovers.timeregistration.client.view.Views;

public class MenuFactory {

	public MenuFactory() {

	}

	public static final HorizontalLayout getMenuComponent(final Navigator navigator) {
		Button[] menu = getMenu(navigator);
		HorizontalLayout comp = new HorizontalLayout(menu);
		
		return comp;
	}
	
	public static final Button[] getMenu(final Navigator navigator) {
		Button[] menu = new Button[5];

		
		menu[0] = new Button("Home",new Button.ClickListener() {
         
			private static final long serialVersionUID = 1L;

			@Override
         public void buttonClick(ClickEvent event) {
             navigator.navigateTo("");
         }
     });

		
		

		
		menu[1] =new Button("Provider",new Button.ClickListener() {
         
			private static final long serialVersionUID = 1L;

			@Override
         public void buttonClick(ClickEvent event) {
             navigator.navigateTo(Views.PROVIDER.toString());
         }
     });
		menu[2] = new Button("3");
		menu[3] = new Button("4");
		menu[4] = new Button("5");

		return menu;

	}
}
