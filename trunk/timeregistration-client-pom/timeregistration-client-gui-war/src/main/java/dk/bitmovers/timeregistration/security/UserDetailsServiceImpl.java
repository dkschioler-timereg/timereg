package dk.bitmovers.timeregistration.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserProvider userProvider;

	public UserDetailsServiceImpl() {

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userProvider.retrieveUserByName(username);
		logger.debug("loadUserByUsername:{}", user);
		if (user != null) {
			List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList("ROLE_USER");

			return new UserDetailsImpl(auths, true, true, true, true, user);
		} else {
			throw new UsernameNotFoundException("username=" + username);
		}
	}
}
