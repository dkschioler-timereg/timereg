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
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.model.Client;

public class ClientComponent extends AbstractTimeregComboBoxComponent {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ClientComponent(TimeregistrationEventListener timeregistrationEventListener, String style) {
		super(timeregistrationEventListener, style, "Client: ", new ComboBox());
		logger.debug("constructor, {}", this);

	}

	@Override
	protected void update(TimeRegistrationSession trSession) {
		//		clients.clear();
		List<Client> clients = trSession.getClients();
		logger.debug("fillClients: {}", clients);
		ComboBox clientsCmb = (ComboBox) component;

		for (Client c : clients) {
			logger.debug("adding client={}", c.getName());
			clientsCmb.addItem(c.getName());
		}
		clientsCmb.setTextInputAllowed(false);

		//		lbl.setCaption("Client: ");

		Client currentClient = trSession.getCurrentClient();
		//		String curClient = "n/a"
		if (currentClient != null) {

			clientsCmb.setValue(currentClient.getName());
		}
		//		status.setCaption("Current selected client: " + curClient);

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
					TimeregistrationEvent te = new TimeregistrationEventClientUpdate(value);
					timeregistrationEventListener.handleEvent(te);
				} else {
					logger.info("Skipping event, since value = null");
				}

			}
		};

		return cl;
	}

}
