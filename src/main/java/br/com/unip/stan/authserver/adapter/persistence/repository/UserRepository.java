package br.com.unip.stan.authserver.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unip.stan.authserver.adapter.persistence.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}
