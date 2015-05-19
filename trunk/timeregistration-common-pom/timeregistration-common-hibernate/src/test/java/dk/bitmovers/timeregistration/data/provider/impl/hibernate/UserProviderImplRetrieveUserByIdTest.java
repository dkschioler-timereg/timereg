package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.UserPassword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring-ctx.xml" })
public class UserProviderImplRetrieveUserByIdTest extends AbstractHibernateTestBase {

	@Autowired
	UserProvider userProvider;

	public boolean doTest() {
		logger.debug("testRetrieveUserByName");
		logger.debug("" + userProvider);

		User user = userProvider.retrieveUserByName("lasc");
		logger.debug("user={}", user);
		assertNotNull(user);
		assertEquals("lasc", user.getLogin());
		Set<UserPassword> userPasswords = user.getUserPasswords();
		logger.debug("userPasswords={}", userPasswords);
		assertNotNull(userPasswords);
		assertEquals(1, userPasswords.size());
		return true;
	}

	@Test
	public void testRetrieveUserById() {

	}

}
