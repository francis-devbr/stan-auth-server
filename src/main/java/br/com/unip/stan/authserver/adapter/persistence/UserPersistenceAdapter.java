package br.com.unip.stan.authserver.adapter.persistence;

import org.springframework.stereotype.Component;

import br.com.unip.stan.authserver.adapter.persistence.repository.UserRepository;
import br.com.unip.stan.authserver.domain.Usuario;
import br.com.unip.stan.authserver.usecase.port.out.LoginPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoginPort {

	private final UserRepository userRepository;

	public Usuario findByUsername(String username) {
		return UserMapper.maptToDomainEntity(userRepository.findByUsername(username)).orElse(null);
	}

}