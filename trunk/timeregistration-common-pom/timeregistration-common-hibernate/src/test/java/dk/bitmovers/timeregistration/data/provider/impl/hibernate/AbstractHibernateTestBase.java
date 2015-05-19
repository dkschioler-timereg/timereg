package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.UserPassword;

public abstract class AbstractHibernateTestBase {

	static {
		PropertyConfigurator.configure("src/test/resources/test-log4j.properties");
	}
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;

	public AbstractHibernateTestBase() {

	}

	@Test
	public void testBase() {

		boolean rollback = true;
		try {
			session = sessionFactory.openSession();

			session.getTransaction().begin();
			
			rollback = doTest();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		} finally {
			if (session != null) {
				if (rollback) {
					session.getTransaction().rollback();
				} else {
					session.getTransaction().commit();
				}
				session.close();
			}
		}
	}

	protected Session getSession() {
		return session;
	}

	public abstract boolean doTest();
}
