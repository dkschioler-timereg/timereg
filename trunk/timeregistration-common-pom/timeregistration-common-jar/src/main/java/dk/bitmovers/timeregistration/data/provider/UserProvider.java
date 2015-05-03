package dk.bitmovers.timeregistration.data.provider;

import dk.bitmovers.timeregistration.model.User;

public interface UserProvider {

	public User retrieveUserByName(String user) throws TimeregistrationProviderException;
	public User retrieveUserById(String id) throws TimeregistrationProviderException;
	
}
