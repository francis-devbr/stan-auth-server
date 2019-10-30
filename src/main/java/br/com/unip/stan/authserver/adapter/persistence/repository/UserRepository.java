package br.com.unip.stan.authserver.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unip.stan.authserver.adapter.persistence.entity.UsuarioJpaEntity;

public interface UserRepository extends JpaRepository<UsuarioJpaEntity, Long> {

	Optional<UsuarioJpaEntity> findByUsername(String username);

}
