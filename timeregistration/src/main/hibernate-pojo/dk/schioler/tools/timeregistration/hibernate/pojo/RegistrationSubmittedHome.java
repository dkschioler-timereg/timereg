package dk.schioler.tools.timeregistration.hibernate.pojo;

// Generated 03-04-2015 09:48:36 by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class RegistrationSubmitted.
 * @see dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationSubmitted
 * @author Hibernate Tools
 */
public class RegistrationSubmittedHome {

	private static final Log log = LogFactory
			.getLog(RegistrationSubmittedHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(RegistrationSubmitted transientInstance) {
		log.debug("persisting RegistrationSubmitted instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegistrationSubmitted instance) {
		log.debug("attaching dirty RegistrationSubmitted instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegistrationSubmitted instance) {
		log.debug("attaching clean RegistrationSubmitted instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RegistrationSubmitted persistentInstance) {
		log.debug("deleting RegistrationSubmitted instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegistrationSubmitted merge(RegistrationSubmitted detachedInstance) {
		log.debug("merging RegistrationSubmitted instance");
		try {
			RegistrationSubmitted result = (RegistrationSubmitted) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegistrationSubmitted findById(int id) {
		log.debug("getting RegistrationSubmitted instance with id: " + id);
		try {
			RegistrationSubmitted instance = (RegistrationSubmitted) sessionFactory
					.getCurrentSession()
					.get("dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationSubmitted",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RegistrationSubmitted instance) {
		log.debug("finding RegistrationSubmitted instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationSubmitted")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
