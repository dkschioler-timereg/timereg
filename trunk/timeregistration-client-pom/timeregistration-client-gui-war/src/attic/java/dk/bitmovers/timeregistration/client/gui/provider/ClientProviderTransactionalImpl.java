package dk.bitmovers.timeregistration.client.gui.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;

@SuppressWarnings("unchecked")
public class ClientProviderTransactionalImpl extends AbstractTransactionWrapper implements ClientProvider {
	
	@Autowired
	ClientProvider clientProvider;

	public ClientProviderTransactionalImpl() {

	}

	public Client persistClient(Client client) throws TimeregistrationProviderException {
		return clientProvider.persistClient(client);
	}

	public Client updateClient(Client client) throws TimeregistrationProviderException {
		return clientProvider.updateClient(client);
	}

	public int deleteClient(long clientId) throws TimeregistrationProviderException {
		return clientProvider.deleteClient(clientId);
	}

	public Client retrieveClient(long clientId) throws TimeregistrationProviderException {
		return clientProvider.retrieveClient(clientId);
	}

	public List<Client> queryClients(final SearchCriteria criteria) throws TimeregistrationProviderException {
		
		return  (List<Client>) run(new Executor() {
			@Override
			public Object doIt() {
				return clientProvider.queryClients(criteria);
			}
		});
		
	}

	public ClientProject persistClientProject(ClientProject clientProject) throws TimeregistrationProviderException {
		return clientProvider.persistClientProject(clientProject);
	}

	public ClientProject updateClientProject(ClientProject clientProject) throws TimeregistrationProviderException {
		return clientProvider.updateClientProject(clientProject);
	}

	public int deleteClientProject(long clientProjectId) throws TimeregistrationProviderException {
		return clientProvider.deleteClientProject(clientProjectId);
	}

	public ClientProject retrieveClientProject(long clientProjectId) throws TimeregistrationProviderException {
		return clientProvider.retrieveClientProject(clientProjectId);
	}

	public List<ClientProject> queryClientProjects(SearchCriteria criteria) throws TimeregistrationProviderException {
		return clientProvider.queryClientProjects(criteria);
	}

	

}
