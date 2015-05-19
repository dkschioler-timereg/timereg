package dk.bitmovers.timeregistration.client.gui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.gridlayout.GridLayoutState;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
//
//@SuppressWarnings("serial")
//@Theme("valo.util.bourbon")
//@Title("kost")

public class Timeregistration_client_gui_warUI extends UI {

	@WebServlet(value = { "/welcome.ui", "/welcome.ui/UIDL/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Timeregistration_client_gui_warUI.class)
	public static class Servlet extends VaadinServlet {
	}

	TextField date = new TextField();
	OptionGroup optionGroup = new OptionGroup();
	Tree tree = new Tree("My Tree");

	@Override
	protected void init(VaadinRequest request) {

		//
		//		Table table = new Table("My Table");
		//		table.addItem("i1");
		//		table.addItem("i2");
		//		table.addItem("i3");
		//
		//		table.setSizeUndefined();
		//
		//		hor.addComponent(table);
		//		hor.setExpandRatio(table, 1); // Expand to fill

		//		configureComponents();
		//		contfigureLayouts();

	}

	private void configureComponents() {
		optionGroup.addItem("oid");
		optionGroup.addItem("oid2");
		optionGroup.addItem("oid3");

		optionGroup.addItem("oid4");
		optionGroup.setImmediate(true);
		//		optionGroup.addValueChangeListener(new Property.ValueChangeListener() {
		//
		//			@Override
		//			public void valueChange(ValueChangeEvent event) {
		//				System.out.println("dut" + event.getProperty().getValue());
		//
		//			}
		//		});

		//		optionGroup.setVisible(true);

		//		String[] root = { "Root" };
		//		Object[][] level1 = {
		//				new Object[]{"Branch 1",
		//						"Branch 2" };
		//				}
		//				

		//		String[] b1 = { "Leaf1", "Leaf2", "Leaf3" };
		//		String[] b2 = { "b2.Leaf1", "b2.Leaf2", "b2.Leaf3" };
		//
		//		for (int i = 0; i < root.length; i++) {
		//			tree.addItem(root[i]);
		//			for (int j = 0; j < level1.length; j++) {
		//				
		//			}
		//			
		//		}

	}

	private void contfigureLayouts() {
		VerticalLayout vLayout = new VerticalLayout();
		//		vLayout.setHeight("600");
		//		vLayout.setWidth("800");
		//
		//		setContent(vLayout); // Attach to the UI
		//		vLayout.setCaption("Timeregistration");

		vLayout.setSizeFull();

		CssLayout cssLayout1 = new CssLayout(optionGroup);
		CssLayout cssLayout2 = new CssLayout(tree);
		cssLayout1.setStyleName("responsive");
		cssLayout2.setStyleName("responsive");
		cssLayout1.setSizeFull();
		cssLayout2.setSizeFull();

		Responsive.makeResponsive(cssLayout1);
		Responsive.makeResponsive(cssLayout2);

		// Layout inside layout
		//		HorizontalLayout hor = new HorizontalLayout();
		//		hor.setSizeFull(); // Use all available space
		//		hor.setMargin(true);

		vLayout.addComponent(cssLayout1);
		vLayout.addComponent(cssLayout2);
		vLayout.setExpandRatio(cssLayout1, 1);
		vLayout.setExpandRatio(cssLayout2, 2);
		// Couple of horizontally laid out components
		vLayout.setVisible(true);

		cssLayout1.setHeight("100");
		cssLayout2.setHeight("200");
		cssLayout1.setVisible(true);
		cssLayout2.setVisible(true);

		setContent(vLayout);
	}
}