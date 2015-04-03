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
 * Home object for domain model class VwUserRegistrationEvent.
 * @see dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationEvent
 * @author Hibernate Tools
 */
public class VwUserRegistrationEventHome {

	private static final Log log = LogFactory
			.getLog(VwUserRegistrationEventHome.class);

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

	public void persist(VwUserRegistrationEvent transientInstance) {
		log.debug("persisting VwUserRegistrationEvent instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VwUserRegistrationEvent instance) {
		log.debug("attaching dirty VwUserRegistrationEvent instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VwUserRegistrationEvent instance) {
		log.debug("attaching clean VwUserRegistrationEvent instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VwUserRegistrationEvent persistentInstance) {
		log.debug("deleting VwUserRegistrationEvent instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VwUserRegistrationEvent merge(
			VwUserRegistrationEvent detachedInstance) {
		log.debug("merging VwUserRegistrationEvent instance");
		try {
			VwUserRegistrationEvent result = (VwUserRegistrationEvent) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VwUserRegistrationEvent findById(
			dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationEventId id) {
		log.debug("getting VwUserRegistrationEvent instance with id: " + id);
		try {
			VwUserRegistrationEvent instance = (VwUserRegistrationEvent) sessionFactory
					.getCurrentSession()
					.get("dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationEvent",
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

	public List findByExample(VwUserRegistrationEvent instance) {
		log.debug("finding VwUserRegistrationEvent instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"dk.schioler.tools.timeregistration.hibernate.pojo.VwUserRegistrationEvent")
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
