package dk.bitmovers.timeregistration.client.gui.vaadin.components;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStart;
import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventWorkClockStop;

public class WorkClockComponent extends CssLayout {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected final Label lbl;

	protected final Button buttonStop;
	protected final Button buttonStart;

	protected final TimeregistrationEventListener timeregistrationEventListener;

	public WorkClockComponent(TimeregistrationEventListener timeregistrationEventListener) {
		this.timeregistrationEventListener = timeregistrationEventListener;
		logger.debug("constructor, {}", this);
		this.buttonStart = new Button("Start");
		this.buttonStart.addClickListener(clStart);
		this.buttonStop = new Button("Stop");
		this.buttonStop.addClickListener(clStop);
		this.lbl = new Label("WorkClock:");

		addComponent(lbl);
		addComponent(buttonStart);
		addComponent(buttonStop);
	}

	ClickListener clStart = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			TimeregistrationEvent te = new TimeregistrationEventWorkClockStart(new Date());
			timeregistrationEventListener.handleEvent(te);
		}
	};

	ClickListener clStop = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {

			TimeregistrationEvent te = new TimeregistrationEventWorkClockStop(new Date());
			timeregistrationEventListener.handleEvent(te);

		}
	};

}
