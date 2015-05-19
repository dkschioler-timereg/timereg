package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dk.bitmovers.timeregistration.common.messages.MessageKeys;
import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;

public abstract class AbstractHibernateProviderBase {

	@Autowired
	SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractHibernateProviderBase() {

	}

	protected TimeregistrationProviderException handleException(Exception e, String key) {
		logger.error(e.getMessage(), e);
		return new TimeregistrationProviderException(e);
	}

	public static interface ProviderExecutor {
		public Object doIt(Session session);
	}

	protected Object execute(ProviderExecutor executor) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.getTransaction().begin();

			Object o = executor.doIt(session);
			session.getTransaction().commit();
			return o;

		} catch (TimeregistrationProviderException e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			throw e;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			throw handleException(e, MessageKeys.PROVIDER_GENERAL_ERROR_SHARED);
		} finally {
			session.close();
		}
	}

}
