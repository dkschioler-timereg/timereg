package dk.bitmovers.timeregistration.client.gui.vaadin.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEventListener;
import dk.bitmovers.timeregistration.client.gui.util.TimeregStyle;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;

public abstract class AbstractTimeregComboBoxComponent extends CssLayout {

	private static final long serialVersionUID = 1L;

	protected final Label lbl;

	protected final Component component;

	protected final Button button;

	protected final TimeregistrationEventListener timeregistrationEventListener;

	public AbstractTimeregComboBoxComponent(TimeregistrationEventListener timeregistrationEventListener, String lblText, Component component) {
		this(timeregistrationEventListener, "", lblText, component);
	}

	public AbstractTimeregComboBoxComponent(TimeregistrationEventListener timeregistrationEventListener, String style, String lblText,
			Component component) {
		addStyleName(style);
		this.timeregistrationEventListener = timeregistrationEventListener;
		lbl = new Label(lblText);
		lbl.setWidth("150");
		lbl.addStyleName(TimeregStyle.TR_COMPONENT_LABEL);

		this.component = component;
		this.component.addStyleName(TimeregStyle.TR_COMPONENT);

		this.button = new Button("Set");
		this.button.addStyleName(TimeregStyle.TR_COMPONENT_BUTTON);
		this.button.addClickListener(getClickListener());

		addComponent(lbl);
		addComponent(component);
		addComponent(button);

	}

	protected abstract ClickListener getClickListener();

	public Label getLabel() {
		return lbl;
	}

	public Component getComponent() {
		return component;
	}

	public Button getButton() {
		return button;
	}

	protected abstract void update(TimeRegistrationSession trSession);

	public void updateContent(TimeRegistrationSession trSession) {
		update(trSession);
	}

}
