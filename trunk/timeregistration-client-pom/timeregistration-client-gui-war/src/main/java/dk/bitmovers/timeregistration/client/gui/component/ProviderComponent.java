package dk.bitmovers.timeregistration.client.gui.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Provider;

public class ProviderComponent extends AbstractTimeregistrationComponent implements ValueChangeListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private GridLayout layout = new GridLayout(2, 1);

	protected final Label lbl = new Label("Provider");

	protected final ComboBox component = new ComboBox();

	public ProviderComponent(String style) {
		super(style);

		logger.debug("constructor, {}", this);

		lbl.setWidth(String.valueOf("Provider".length()) + "em");
		logger.debug("constructor, {}", this);
		layout.addComponent(lbl);
		layout.addComponent(component);
		setCompositionRoot(layout);

		component.addValueChangeListener(this);
		// HorizontalLayout verticalLayout = new HorizontalLayout();
		// verticalLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		//
		// verticalLayout.addComponent(lbl);
		// verticalLayout.addComponent(providerCmb);
		// verticalLayout.addComponent(status);

		// verticalLayout.setMargins(true);

		// Panel panel = new Panel("Menu");
		//
		// panel.setContent(verticalLayout);
		//
		// panel.getContent().setSizeUndefined();
		// panel.setSizeUndefined();

		// setSizeUndefined();
		// panel.setWidth("400");

		// logger.debug("constructor");
	}



	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
		// clients.clear();
		ComboBox providerCmb = (ComboBox) component;
		List<Provider> clients = trSession.getProviders();
		logger.debug("updateContent: {}", clients);

		providerCmb.removeItem("");
		for (Provider c : clients) {

			logger.debug("adding provider={}", c.getName());
			providerCmb.addItem(c.getName());
		}

		providerCmb.setValue(null);
		providerCmb.setTextInputAllowed(false);


	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (component.getValue() != null) {
			String value = (String) component.getValue();
			component.setValue(value);
			TimeregistrationEvent te = new TimeregistrationEventProviderUpdate(value);
			this.notify(te);

		}

	}

	// @Override
	// protected ClickListener getClickListener() {
	//
	// ClickListener cl = new ClickListener() {
	//
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public void buttonClick(ClickEvent event) {
	// ComboBox clientsCmb = (ComboBox) component;
	// String value = (String) clientsCmb.getValue();
	// logger.debug("Value=" + value);
	//
	// if (value != null) {
	// TimeregistrationEvent te = new TimeregistrationEventProviderUpdate(value);
	// timeregistrationEventListener.handleEvent(te);
	// } else {
	// logger.info("Skipping event, since value = null");
	// }
	//
	// }
	// };
	// return cl;
	// }
}
