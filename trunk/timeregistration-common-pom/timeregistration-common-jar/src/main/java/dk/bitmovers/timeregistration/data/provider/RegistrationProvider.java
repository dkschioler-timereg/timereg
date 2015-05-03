package dk.bitmovers.timeregistration.data.provider;

import java.util.List;

import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

public interface RegistrationProvider {
	public List<RegistrationItem> getRegistrationItems(User user);
}
