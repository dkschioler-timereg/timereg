package dk.bitmovers.timeregistration.client.gui.component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.data.DataProvider;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStart;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStop;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;

public class WorkClockComponent extends AbstractTimeregistrationComponent implements ClickListener {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	GridLayout layout = new GridLayout(3, 1);
	protected final Label lbl;

	protected final Button buttonStop;
	protected final Button buttonStart;

	public WorkClockComponent(String style) {
		super(style);

		logger.debug("constructor, {}", this);
		this.buttonStart = new Button("Start");
		this.buttonStart.addClickListener(this);
		this.buttonStart.setId("Start");
		this.buttonStop = new Button("Stop");
		this.buttonStop.addClickListener(this);
		this.buttonStop.setId("Stop");
		this.lbl = new Label("WorkClock:");

		layout.addComponent(lbl);
		layout.addComponent(buttonStart);
		layout.addComponent(buttonStop);
		setCompositionRoot(layout);
	}

	@Override
	public void updateContent(WrappedSession session, TimeRegistrationSession trSession, DataProvider dataProvider) {

	}

	@Override
	public void buttonClick(ClickEvent event) {
		Object source = event.getSource();
		logger.debug("{}", event);
		if (source instanceof Button) {
			Button b = (Button) source;
			logger.debug("Event={}", event);
			Button button = event.getButton();
			logger.debug("button={}", button);
			TimeregistrationEvent te = null;
			if (b.getId().equals(this.buttonStart.getId())) {
				te = new TimeregistrationEventWorkClockStart(new Date());
			} else if (b.getId().equals(this.buttonStop.getId())) {
				te = new TimeregistrationEventWorkClockStop(new Date());
			}
			this.notify(te);

		}

	}

}
