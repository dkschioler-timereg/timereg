package dk.bitmovers.timeregistration.client.gui.provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractTransactionWrapper {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SessionFactory sessionFactory;

	public AbstractTransactionWrapper() {

	}

	protected Object run(Executor e) {
		Object types = null;
		Session openSession = null;
		Transaction transaction = null;
		try {
			openSession = sessionFactory.openSession();

			logger.debug("openSession=" + openSession);

			transaction = openSession.beginTransaction();

			logger.debug("beginTransaction=" + transaction.isActive());
			types = e.doIt();
			transaction.commit();
		} catch (Exception exc) {
			transaction.rollback();
			logger.error(exc.toString(), exc);
		} finally {
			if (openSession != null) {
				openSession.close();
			}
		}
		logger.debug("returning from event mix...");
		return types;
	}

	public interface Executor {
		public Object doIt();
	}

}
