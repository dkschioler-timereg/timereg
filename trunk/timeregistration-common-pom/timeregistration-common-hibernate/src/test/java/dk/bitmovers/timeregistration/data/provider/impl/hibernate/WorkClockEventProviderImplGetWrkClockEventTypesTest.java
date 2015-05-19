package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import static org.junit.Assert.*;

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
import dk.bitmovers.timeregistration.model.WorkClockEventType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class WorkClockEventProviderImplGetWrkClockEventTypesTest extends AbstractHibernateTestBase {

	@Autowired
	WorkClockEventProvider workClockEventProvider;

	@Override
	public boolean doTest() {
		List<WorkClockEventType> workClockEventTypes = workClockEventProvider.getWorkClockEventTypes();
		assertNotNull(workClockEventTypes);

		assertEquals(2, workClockEventTypes.size());
		boolean stopFound = false;
		boolean startFound = false;
		for (WorkClockEventType workClockEventType : workClockEventTypes) {
			if ("start".equalsIgnoreCase(workClockEventType.getEventType())) {
				startFound = true;
			} else if ("stop".equalsIgnoreCase(workClockEventType.getEventType())) {
				stopFound = true;
			}
		}

		assertTrue(startFound);
		assertTrue(stopFound);

		return true;
	}
}
