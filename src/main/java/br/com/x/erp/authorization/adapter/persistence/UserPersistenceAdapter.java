package br.com.x.erp.authorization.adapter.persistence;

import org.springframework.stereotype.Component;

import br.com.x.erp.authorization.adapter.persistence.entity.Usuario;
import br.com.x.erp.authorization.adapter.persistence.repository.UserRepository;
import br.com.x.erp.authorization.usecase.port.out.LoginPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoginPort {

	private final UserRepository userRepository;

	public Usuario findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

}