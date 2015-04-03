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
 * Home object for domain model class VwUserRegistrationItem.
 * @see dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationItem
 * @author Hibernate Tools
 */
public class VwUserRegistrationItemHome {

	private static final Log log = LogFactory
			.getLog(VwUserRegistrationItemHome.class);

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

	public void persist(VwUserRegistrationItem transientInstance) {
		log.debug("persisting VwUserRegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VwUserRegistrationItem instance) {
		log.debug("attaching dirty VwUserRegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VwUserRegistrationItem instance) {
		log.debug("attaching clean VwUserRegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VwUserRegistrationItem persistentInstance) {
		log.debug("deleting VwUserRegistrationItem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VwUserRegistrationItem merge(VwUserRegistrationItem detachedInstance) {
		log.debug("merging VwUserRegistrationItem instance");
		try {
			VwUserRegistrationItem result = (VwUserRegistrationItem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VwUserRegistrationItem findById(
			dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationItemId id) {
		log.debug("getting VwUserRegistrationItem instance with id: " + id);
		try {
			VwUserRegistrationItem instance = (VwUserRegistrationItem) sessionFactory
					.getCurrentSession()
					.get("dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationItem",
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

	public List findByExample(VwUserRegistrationItem instance) {
		log.debug("finding VwUserRegistrationItem instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationItem")
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
