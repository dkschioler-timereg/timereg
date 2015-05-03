package dk.bitmovers.timeregistration.data.provider;

import java.util.List;

import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;

public interface ClientProvider {
	public Client persistClient(Client client) throws TimeregistrationProviderException;

	public Client updateClient(Client client) throws TimeregistrationProviderException;

	public int deleteClient(long clientId) throws TimeregistrationProviderException;

	public Client retrieveClient(long clientId) throws TimeregistrationProviderException;

	public List<Client> queryClients(SearchCriteria criteria) throws TimeregistrationProviderException;

	public ClientProject persistClientProject(ClientProject clientProject) throws TimeregistrationProviderException;

	public ClientProject updateClientProject(ClientProject clientProject) throws TimeregistrationProviderException;

	public int deleteClientProject(long clientProjectId) throws TimeregistrationProviderException;

	public ClientProject retrieveClientProject(long clientProjectId) throws TimeregistrationProviderException;

	public List<ClientProject> queryClientProjects(SearchCriteria criteria) throws TimeregistrationProviderException;
}
