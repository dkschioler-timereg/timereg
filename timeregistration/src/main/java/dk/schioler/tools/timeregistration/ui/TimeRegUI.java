package dk.schioler.tools.timeregistration.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SuppressWarnings("serial")
@SpringUI
public class TimeRegUI extends UI {

	@WebServlet(urlPatterns = {"/ui/*","/VAADIN/*"}, asyncSupported = true)
	// @VaadinServletConfiguration(productionMode = false, ui = TimeRegUI.class,
	// widgetset = "com.vaadin.spring.tutorial.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	// @WebListener
	// public static class MyContextLoaderListener extends ContextLoaderListener
	// {
	// }

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}

		});
		layout.addComponent(button);
		this.setContent(layout);
	}

}
