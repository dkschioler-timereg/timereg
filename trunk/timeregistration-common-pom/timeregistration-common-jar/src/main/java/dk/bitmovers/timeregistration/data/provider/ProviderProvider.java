package dk.bitmovers.timeregistration.data.provider;

import java.util.List;

import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;

public interface ProviderProvider {
	public Provider persistProvider(Provider provider) throws TimeregistrationProviderException;

	public Provider updateClient(Provider provider) throws TimeregistrationProviderException;

	public int deleteProvider(long id) throws TimeregistrationProviderException;

	public Provider retrieveProvider(long id) throws TimeregistrationProviderException;

	public List<Provider> queryProviders(SearchCriteria criteria) throws TimeregistrationProviderException;

	public ProviderAccount persistProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException;

	public ProviderAccount updateProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException;

	public int deleteProviderAccount(long clientProjectId) throws TimeregistrationProviderException;

	public ProviderAccount retrieveProviderAccount(long clientProjectId) throws TimeregistrationProviderException;

}
