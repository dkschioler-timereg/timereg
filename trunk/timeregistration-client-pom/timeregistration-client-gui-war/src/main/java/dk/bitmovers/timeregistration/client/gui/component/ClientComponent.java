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
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventClientUpdate;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.SearchCriteriaImpl;
import dk.bitmovers.timeregistration.model.Client;

public class ClientComponent extends AbstractTimeregistrationComponent implements ValueChangeListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private GridLayout layout = new GridLayout(2, 1);

	protected final Label lbl = new Label("Client");

	protected final ComboBox component = new ComboBox();

	// protected final TimeregistrationEventListener timeregEventListener;

	public ClientComponent(String style) {
		super(style);
		lbl.setWidth(String.valueOf("Client".length()) + "em");
		logger.debug("constructor, {}", this);
		layout.addComponent(lbl);
		layout.addComponent(component);
		setCompositionRoot(layout);

		component.addValueChangeListener(this);
	}

	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {

		SearchCriteria sc = new SearchCriteriaImpl();
		sc.getCriteria().put(SearchCriteria.USER_ID, String.valueOf(trSession.getUser().getId()));
		List<Client> clients = dataProvider.getClientProvider().queryClients(sc);
		logger.debug("fillClients: {}", clients);
		ComboBox clientsCmb = (ComboBox) component;

		int maxWidth = 0;
		for (Client c : clients) {
			logger.debug("adding client={}", c.getName());
			maxWidth = Math.max(maxWidth, c.getName().length());
			clientsCmb.setItemCaption(String.valueOf(c.getId()), c.getName());
			clientsCmb.addItem(String.valueOf(c.getId()));
		}
		clientsCmb.setTextInputAllowed(false);
		clientsCmb.setWidth(String.valueOf(maxWidth) + "em");

		Client currentClient = trSession.getCurrentClient();

		if (currentClient != null) {
			clientsCmb.setValue(currentClient.getId());
		}

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (component.getValue() != null) {
			String value = (String) component.getValue();
			component.setValue(value);
			String itemCaption = component.getItemCaption(value);
			TimeregistrationEvent te = new TimeregistrationEventClientUpdate(value, itemCaption);
			this.notify(te);
		}
	}

}
