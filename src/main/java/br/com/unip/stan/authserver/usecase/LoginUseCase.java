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

import br.com.unip.stan.authserver.adapter.persistence.entity.Regra;
import br.com.unip.stan.authserver.adapter.persistence.entity.Usuario;
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
					getAuthorities(user.getRegras()));
			return userDetails;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private final Collection<? extends GrantedAuthority> getAuthorities(Collection<Regra> roles) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Regra role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getNome()));
			authorities.addAll(
					role.getPrivilegios()
					.stream()
					.map(p -> new SimpleGrantedAuthority(p.getNome()))
					.collect(Collectors.toList()));
		}
		return authorities;
	}
}