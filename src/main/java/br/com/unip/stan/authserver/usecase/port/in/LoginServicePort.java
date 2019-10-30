package br.com.unip.stan.authserver.usecase.port.in;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginServicePort extends UserDetailsService {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
