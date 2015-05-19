package dk.bitmovers.timeregistration.client.gui.vaadin.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventProviderUpdate;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Provider;

public class ProviderComponent extends AbstractTimeregComboBoxComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProviderComponent(TimeregistrationEventListener timeregistrationEventListener) {
		super(timeregistrationEventListener, "Provider: ", new ComboBox());
		logger.debug("constructor, {}", this);

		//		HorizontalLayout verticalLayout = new HorizontalLayout();
		//		verticalLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		//
		//		verticalLayout.addComponent(lbl);
		//		verticalLayout.addComponent(providerCmb);
		//		verticalLayout.addComponent(status);

		//		verticalLayout.setMargins(true);

		//		Panel panel = new Panel("Menu");
		//
		//		panel.setContent(verticalLayout);
		//
		//		panel.getContent().setSizeUndefined();
		//		panel.setSizeUndefined();

		//		setSizeUndefined();
		//		panel.setWidth("400");

		//		logger.debug("constructor");
	}

	@Override
	protected void update(TimeRegistrationSession trSession) {

		//		clients.clear();
		ComboBox providerCmb = (ComboBox) component;
		List<Provider> clients = trSession.getProviders();
		logger.debug("updateContent: {}", clients);
		String def = null;
		int idx = 0;
		providerCmb.removeItem("");
		for (Provider c : clients) {
			if (idx == 0) {
				def = c.getName();
			}
			idx++;
			logger.debug("adding provider={}", c.getName());
			providerCmb.addItem(c.getName());
		}
		//		clientsCmb.select(def);
		providerCmb.setTextInputAllowed(false);
		//		lbl.setCaption("Provider: ");
		//
		//		Provider currentClient = trSession.getCurrentProvider();
		//		String curClient = "n/a";
		//		if (currentClient != null) {
		//			curClient = currentClient.getName();
		//		}
		//		status.setCaption("Current selected provider: " + curClient);
	}

	@Override
	protected ClickListener getClickListener() {

		ClickListener cl = new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ComboBox clientsCmb = (ComboBox) component;
				String value = (String) clientsCmb.getValue();
				logger.debug("Value=" + value);

				if (value != null) {
					TimeregistrationEvent te = new TimeregistrationEventProviderUpdate(value);
					timeregistrationEventListener.handleEvent(te);
				} else {
					logger.info("Skipping event, since value = null");
				}

			}
		};
		return cl;
	}
}
