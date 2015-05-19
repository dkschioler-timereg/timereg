package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dk.bitmovers.timeregistration.data.provider.TimeregistrationProviderException;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@Component("workClockEventProvider")
public class WorkClockEventProviderImpl extends AbstractHibernateProviderBase implements WorkClockEventProvider {

	public WorkClockEventProviderImpl() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
	@Transactional
	public List<WorkClockEventType> getWorkClockEventTypes() throws TimeregistrationProviderException {
		return (List<WorkClockEventType>) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Query query = session.createQuery("from WorkClockEventType");

				List list = query.list();
				return list;
			}
		});

	}

	@Override
	@Transactional
	public Integer saveWorkClockEvent(final WorkClockEvent event) throws TimeregistrationProviderException {
		return (Integer) this.execute(new ProviderExecutor() {

			@Override
			public Object doIt(Session session) {
				Serializable save = session.save(event);
				return save;
			}
		});
	}

	@Override
	public WorkClockEvent deleteWorkClockEvent(WorkClockEvent event) throws TimeregistrationProviderException {

		return null;
	}

}
