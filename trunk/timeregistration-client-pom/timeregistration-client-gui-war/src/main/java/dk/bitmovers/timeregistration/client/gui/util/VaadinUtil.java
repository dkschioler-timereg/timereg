package dk.bitmovers.timeregistration.client.gui.util;

import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.ui.OptionGroup;

public class VaadinUtil {

	
	private VaadinUtil() {

	}

	public static final OptionGroup createOptionGroup(String caption, List<String> options, Property.ValueChangeListener listener) {
		OptionGroup optionGroup = new OptionGroup(caption); 
		for (String string : options) {
			optionGroup.addItem(string);
		}
		
		optionGroup.addValueChangeListener(listener);
		return optionGroup;
	}
	
	
}
