package dk.bitmovers.timeregistration.client.gui.data;

import java.util.List;

import dk.bitmovers.timeregistration.client.gui.ViewManager;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.data.provider.UserProvider;

public interface DataProvider {
	public UserProvider getUserProvider();

	public ClientProvider getClientProvider();

	public ProviderProvider getProviderProvider();

	public RegistrationProvider getRegistrationProvider();

	public List<ViewManager.ViewInfo> getMenuItems();

}
