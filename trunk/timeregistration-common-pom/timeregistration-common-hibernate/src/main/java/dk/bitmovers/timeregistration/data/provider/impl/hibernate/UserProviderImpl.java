package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dk.bitmovers.timeregistration.common.messages.MessageKeys;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.UserPassword;

@Component("userProvider")
public class UserProviderImpl extends AbstractHibernateProviderBase implements UserProvider {

	public UserProviderImpl() {

	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public User retrieveUserByName(final String user) throws TimeregistrationProviderException {
		return (User) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Query query = session
						.createQuery("from User as u left join fetch u.userPasswords where u.login = :login and u.isLoginEnabled = 'Y' ");

				query.setParameter("login", user);
				List list = query.list();
				User rv = getUserAndFillActivePassword(list);
				return rv;
			}
		});

	}

	@SuppressWarnings("rawtypes")
	private User getUserAndFillActivePassword(List list) {
		User rv = null;
		if (list != null) {
			if (list.size() == 1) {
				Object object = list.get(0);

				if (object instanceof User) {
					rv = (User) object;
					Set<UserPassword> active = new LinkedHashSet<UserPassword>();
					Set<UserPassword> userPasswords = rv.getUserPasswords();

					for (UserPassword userPassword : userPasswords) {
						if ("Y".equals(userPassword.getIsActive())) {
							active.add(userPassword);
						}
					}
					if (active.size() != 1) {
						throw new TimeregistrationProviderException(MessageKeys.PROVIDER_USER_ERROR_USER_PASSWORD_COUNT_NOT_EQUALS_ONE);
					}
					rv.setUserPasswords(active);

				} else {
					throw new TimeregistrationProviderException(MessageKeys.PROVIDER_GENERAL_ERROR_SHARED);
				}

			} else {
				throw new TimeregistrationProviderException(MessageKeys.PROVIDER_USER_ERROR_USER_ACTIVE_COUNT_NOT_EQUALS_ONE + " ="
						+ list.size());
			}
		}
		return rv;
	}

	@Override
	@SuppressWarnings("rawtypes")
	@Transactional
	public User retrieveUserById(String id) throws TimeregistrationProviderException {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from User as u left join fetch u.userPasswords where u.id = :id and u.isLoginEnabled = 'Y' ");

		query.setParameter("id", id);
		List list = query.list();
		User rv = getUserAndFillActivePassword(list);
		return rv;
	}

}
