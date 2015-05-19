package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.data.provider.ProviderProvider;
import dk.bitmovers.timeregistration.data.provider.SearchCriteria;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.ProviderAccount;

@Component("providerProvider")
public class ProviderProviderImpl extends AbstractHibernateProviderBase implements ProviderProvider {

	public ProviderProviderImpl() {

	}

	@Override
	public Provider persistProvider(Provider provider) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider updateClient(Provider provider) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProvider(long id) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Provider retrieveProvider(long id) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provider> queryProviders(SearchCriteria criteria) throws TimeregistrationProviderException {
		final String string = criteria.getCriteria().get(SearchCriteria.USER_ID);
		return (List<Provider>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Query query = session.createQuery("from Provider where user_id = :login_id");

				query.setParameter("login_id", string);
				List list = query.list();

				return list;
			}
		});

	}

	@Override
	public ProviderAccount persistProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProviderAccount updateProviderAccount(ProviderAccount clientProject) throws TimeregistrationProviderException {

		return null;
	}

	@Override
	public int deleteProviderAccount(long clientProjectId) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProviderAccount retrieveProviderAccount(long clientProjectId) throws TimeregistrationProviderException {
		// TODO Auto-generated method stub
		return null;
	}

}
