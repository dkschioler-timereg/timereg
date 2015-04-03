package dk.schioler.tools.timeregistration.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<String> roles;;

	private final String login;
	private final String name;

	public User(List<String> roles, String login, String name) {
		super();
		List<String> r = new ArrayList<String>();
		r.addAll(roles);
		this.roles = Collections.unmodifiableList(r);
		this.login = login;
		this.name = name;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}
	
	

}
