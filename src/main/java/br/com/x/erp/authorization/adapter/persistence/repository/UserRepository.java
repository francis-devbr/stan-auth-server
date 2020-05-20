package br.com.x.erp.authorization.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.x.erp.authorization.adapter.persistence.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}
