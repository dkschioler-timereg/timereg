package dk.bitmovers.timeregistration.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.UserPassword;

public class UserDetailsImpl implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;
	private List<GrantedAuthority> authorities;

	private String password;
	boolean isEnabled;
	boolean isAccountNonLocked;
	boolean isAccountNonExpired;
	boolean isCredentialsNonExpired;

	private User user;

	public UserDetailsImpl(List<GrantedAuthority> authorities,  boolean isEnabled,
			boolean isAccountNonLocked, boolean isAccountNonExpired, boolean isCredentialsNonExpired, User user) {
		super();
		this.authorities = authorities;
		
		
		this.isEnabled = isEnabled;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.user = user;
		
		Set<UserPassword> userPassword = user.getUserPasswords();
		
		for (UserPassword up : userPassword) {
			if ("Y".equals(up.getIsActive())) {
				this.password = up.getPassword();
				break;
			}
		}
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	

	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {

		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {

		return isEnabled;
	}

}
