package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.data.provider.WorkClockEventProvider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class WorkClockEventProviderImplSaveWorkClockEventTest extends AbstractHibernateTestBase {

	@Autowired
	WorkClockEventProvider workClockEventProvider;

	@Autowired
	UserProvider userProvider;

	@Override
	public boolean doTest() {
		User retrieveUserByName = userProvider.retrieveUserByName("lasc");
		logger.debug("user="+retrieveUserByName);
		WorkClockEventType wcet = null;
		List<WorkClockEventType> workClockEventTypes = workClockEventProvider.getWorkClockEventTypes();
		for (WorkClockEventType workClockEventType : workClockEventTypes) {
			if (workClockEventType.getEventType().equals("stop")) {
				wcet = workClockEventType;
			}
		}
		if (wcet == null){
			fail("found no workClockEventType");
		}
		WorkClockEvent wce = new WorkClockEvent();
		wce.setCreated(new Date());
		wce.setUser(retrieveUserByName);
		wce.setWorkClockEventType(wcet);

		Integer saveWorkClockEvent = workClockEventProvider.saveWorkClockEvent(wce);
		logger.debug("result=" + saveWorkClockEvent);
		return true;
	}
}
