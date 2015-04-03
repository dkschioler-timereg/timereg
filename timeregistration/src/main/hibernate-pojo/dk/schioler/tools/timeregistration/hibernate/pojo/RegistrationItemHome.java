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
 * Home object for domain model class RegistrationItem.
 * @see dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationItem
 * @author Hibernate Tools
 */
public class RegistrationItemHome {

	private static final Log log = LogFactory
			.getLog(RegistrationItemHome.class);

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

	public void persist(RegistrationItem transientInstance) {
		log.debug("persisting RegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RegistrationItem instance) {
		log.debug("attaching dirty RegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegistrationItem instance) {
		log.debug("attaching clean RegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RegistrationItem persistentInstance) {
		log.debug("deleting RegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegistrationItem merge(RegistrationItem detachedInstance) {
		log.debug("merging RegistrationItem instance");
		try {
			RegistrationItem result = (RegistrationItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RegistrationItem findById(int id) {
		log.debug("getting RegistrationItem instance with id: " + id);
		try {
			RegistrationItem instance = (RegistrationItem) sessionFactory
					.getCurrentSession()
					.get("dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationItem",
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

	public List findByExample(RegistrationItem instance) {
		log.debug("finding RegistrationItem instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"dk.schioler.tools.timeregistration.hibernate.pojo.RegistrationItem")
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
