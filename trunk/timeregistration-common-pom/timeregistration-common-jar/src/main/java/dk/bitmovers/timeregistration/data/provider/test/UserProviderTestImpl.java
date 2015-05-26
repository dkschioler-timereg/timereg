package dk.bitmovers.timeregistration.data.provider.test;

import java.util.Date;

import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;

public class UserProviderTestImpl implements UserProvider {

	@Override
	public User retrieveUserByName(String user) throws TimeregistrationProviderException {
		
		return null;
	}

	@Override
	public User retrieveUserById(String id) throws TimeregistrationProviderException {

		return null;
	}

}
