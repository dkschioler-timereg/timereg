package dk.bitmovers.timeregistration.client.gui.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

public class ProviderAccountComponent extends AbstractTimeregistrationComponent implements TimeregistrationEventListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	GridLayout layout = new GridLayout(3, 1);
	Label providerAccountLbl = new Label("ProviderAccounts");
	ComboBox providerAccounts = new ComboBox();

	public ProviderAccountComponent(String style) {
		super(style);

		layout.addComponent(providerAccountLbl);
		layout.addComponent(providerAccounts);

		setCompositionRoot(layout);

	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {
	

	}

	@Override
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException {
		return false;
	}

	@Override
	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException {
		return null;
	}

	// @Override
	// protected void update(TimeRegistrationSession trSession) {
	// // clients.clear();
	// List<Client> clients = trSession.getClients();
	// logger.debug("fillClients: {}", clients);
	// ComboBox clientsCmb = (ComboBox) component;
	//
	// for (Client c : clients) {
	// logger.debug("adding client={}", c.getName());
	// clientsCmb.addItem(c.getName());
	// }
	// clientsCmb.setTextInputAllowed(false);
	//
	// // lbl.setCaption("Client: ");
	//
	// Client currentClient = trSession.getCurrentClient();
	// // String curClient = "n/a"
	// if (currentClient != null) {
	//
	// clientsCmb.setValue(currentClient.getName());
	// }
	// // status.setCaption("Current selected client: " + curClient);
	//
	// }

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
	// TimeregistrationEvent te = new TimeregistrationEventClientUpdate(value);
	// timeregistrationEventListener.handleEvent(te);
	// } else {
	// logger.info("Skipping event, since value = null");
	// }
	//
	// }
	// };
	//
	// return cl;
	// }

}
