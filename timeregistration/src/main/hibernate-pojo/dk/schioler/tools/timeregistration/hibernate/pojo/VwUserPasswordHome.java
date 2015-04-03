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
 * Home object for domain model class VwUserPassword.
 * @see dk.schioler.tools.timeregistration.hibernate.pojo.VwUserPassword
 * @author Hibernate Tools
 */
public class VwUserPasswordHome {

	private static final Log log = LogFactory.getLog(VwUserPasswordHome.class);

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

	public void persist(VwUserPassword transientInstance) {
		log.debug("persisting VwUserPassword instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VwUserPassword instance) {
		log.debug("attaching dirty VwUserPassword instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VwUserPassword instance) {
		log.debug("attaching clean VwUserPassword instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VwUserPassword persistentInstance) {
		log.debug("deleting VwUserPassword instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VwUserPassword merge(VwUserPassword detachedInstance) {
		log.debug("merging VwUserPassword instance");
		try {
			VwUserPassword result = (VwUserPassword) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VwUserPassword findById(
			dk.schioler.tools.timeregistration.hibernate.pojo.VwUserPasswordId id) {
		log.debug("getting VwUserPassword instance with id: " + id);
		try {
			VwUserPassword instance = (VwUserPassword) sessionFactory
					.getCurrentSession()
					.get("dk.schioler.tools.timeregistration.hibernate.pojo.VwUserPassword",
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

	public List findByExample(VwUserPassword instance) {
		log.debug("finding VwUserPassword instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"dk.schioler.tools.timeregistration.hibernate.pojo.VwUserPassword")
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
