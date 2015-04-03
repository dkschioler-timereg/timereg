package dk.schioler.tools.timeregistration.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import dk.schioler.tools.timeregistration.dto.User;

@Component("name=userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate JdbcTemplate;

	private String columns = "u.id";
	
	@Override
	public User lookupUser(int id) {
		
		return null;
	}

	@Override
	public User lookupUser(String login) {

		return null;
	}

	@Override
	public List<User> lookupUsers() {

		return null;
	}

}
