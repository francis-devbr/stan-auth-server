package br.com.unip.stan.authserver.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.unip.stan.authserver.domain.Role;
import br.com.unip.stan.authserver.domain.Usuario;
import br.com.unip.stan.authserver.usecase.port.in.LoginServicePort;
import br.com.unip.stan.authserver.usecase.port.out.LoginPort;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class LoginUseCase implements LoginServicePort {

	private final LoginPort loginPort;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Usuario user = loginPort.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("No user found with username: " + username);
			}
			org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), user.isEnable(), true, true, true,
					getAuthorities(user.getRoles()));
			return userDetails;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private final Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.addAll(
					role.getPrivileges()
					.stream()
					.map(p -> new SimpleGrantedAuthority(p.getName()))
					.collect(Collectors.toList()));
		}
		return authorities;
	}
}