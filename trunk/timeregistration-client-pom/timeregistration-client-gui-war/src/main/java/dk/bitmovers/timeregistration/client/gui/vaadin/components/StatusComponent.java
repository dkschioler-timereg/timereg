package dk.bitmovers.timeregistration.client.gui.vaadin.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;

public class StatusComponent extends CssLayout {

	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected final Label lbl;
	protected final TextArea textArea;

	public StatusComponent() {
		setWidth("400");
		lbl = new Label("Status:");
		textArea = new TextArea();
		logger.debug("constructor, {}", this);

		addComponent(lbl);
		addComponent(textArea);
		
		
	}

	public void updateStatus(List<String> lines) {
		String status = "";
		for (String string : lines) {
			status = status + string + "\n";
		}
		this.textArea.setValue(status);

	}
}
