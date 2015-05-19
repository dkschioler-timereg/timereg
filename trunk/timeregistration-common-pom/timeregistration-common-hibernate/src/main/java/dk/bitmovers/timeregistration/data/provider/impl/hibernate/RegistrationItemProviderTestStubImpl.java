package dk.bitmovers.timeregistration.data.provider.impl.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.model.RegistrationItem;
import dk.bitmovers.timeregistration.model.User;

@Component("registrationProvider")
public class RegistrationItemProviderTestStubImpl implements RegistrationProvider {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RegistrationItem> getRegistrationItems(User user) {
/*
		List<Client> query = jdbcTemplate.query("select login, client_name,clientproject_name,clientproject_code from vw_client_work where login ='lasc'", new RowMapper<Client>() {


			@Override
			public Client mapRow(ResultSet rs, int arg1) throws SQLException {
	
				Client c = new Client();
				c.setName(rs.getString("client_name"));
				ClientProject cp = new ClientProject();
				cp.setClient(c);
				cp.setCode(rs.getString("clientproject_code"));
				cp.setName(rs.getString("clientproject_name"));
				
				return c;

			}

		});

		
		return query;*/
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<RegistrationItem> items = (List<RegistrationItem>) currentSession.createQuery("From RegistrationItem").list();
		
		return items;
	}

}
