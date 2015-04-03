package dk.schioler.tools.timeregistration.dao;

import java.util.List;

import dk.schioler.tools.timeregistration.dto.User;

public interface UserDao {
	public User lookupUser(int id);
	public User lookupUser(String login);
	public List<User> lookupUsers();
}
