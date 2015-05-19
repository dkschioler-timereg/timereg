package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;

@Component("clientProvider")
public class ClientProviderImpl implements ClientProvider {

	@Autowired
	SessionFactory sessionFactory;

	public ClientProviderImpl() {

	}

	@Override
	public Client persistClient(Client client) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public Client updateClient(Client client) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteClient(long clientId) throws TimeregistrationProviderException {

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Client retrieveClient(long clientId) throws TimeregistrationProviderException {
		Session currentSession = sessionFactory.getCurrentSession();
		Client c = null;
		Query createQuery = currentSession.createQuery("From Client where id = " + clientId);

		List<Client> list = createQuery.list();
		c = list.get(0);

		return c;
	}

	@Override
	public List<Client> queryClients(SearchCriteria criteria) throws TimeregistrationProviderException {
		Session currentSession = sessionFactory.getCurrentSession();

		Map<String, String> criteria2 = criteria.getCriteria();
		String string = criteria2.get("dk.bitmovers.timeregistration.model.User.id");

		Query createQuery = currentSession.createQuery("From Client where user.id = :uid");
		createQuery.setInteger("uid", Integer.parseInt(string));
		@SuppressWarnings("unchecked")
		List<Client> list = createQuery.list();
		return list;
	}

	@Override
	public ClientProject persistClientProject(ClientProject clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public ClientProject updateClientProject(ClientProject clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteClientProject(long clientProjectId) throws TimeregistrationProviderException {

		return 0;
	}

	@Override
	public ClientProject retrieveClientProject(long clientProjectId) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public List<ClientProject> queryClientProjects(SearchCriteria criteria) throws TimeregistrationProviderException {
		String clientId = criteria.getCriteria().get("dk.bitmovers.timeregistration.model.Client.id");
		String userId = criteria.getCriteria().get("dk.bitmovers.timeregistration.model.User.id");
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("From ClientProject where user.id = :uid");
		query.setInteger("uid", Integer.parseInt(userId));
		@SuppressWarnings("unchecked")
		List<ClientProject> list = query.list();

		return list;
	}

}
