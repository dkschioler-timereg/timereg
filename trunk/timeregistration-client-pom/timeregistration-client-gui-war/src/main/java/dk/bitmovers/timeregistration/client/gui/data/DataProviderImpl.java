package dk.bitmovers.timeregistration.client.gui.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.client.gui.ViewManager;
import dk.bitmovers.timeregistration.client.gui.ViewManager.ViewInfo;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.data.provider.UserProvider;

@Component("dataProvider")
public class DataProviderImpl implements DataProvider {

	@Autowired
	UserProvider userProvider;

	@Autowired
	ClientProvider clientProvider;

	@Autowired
	ProviderProvider providerProvider;

	@Autowired
	RegistrationProvider registrationProvider;

	@Override
	public UserProvider getUserProvider() {

		return userProvider;
	}

	@Override
	public ClientProvider getClientProvider() {

		return clientProvider;
	}

	@Override
	public ProviderProvider getProviderProvider() {

		return providerProvider;
	}

	@Override
	public RegistrationProvider getRegistrationProvider() {
		return registrationProvider;
	}

	@Override
	public List<ViewInfo> getMenuItems() {
		return ViewManager.VIEWS;
	}

}
