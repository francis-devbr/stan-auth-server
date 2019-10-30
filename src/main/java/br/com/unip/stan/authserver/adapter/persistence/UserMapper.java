package br.com.unip.stan.authserver.adapter.persistence;

import java.util.Optional;

import br.com.unip.stan.authserver.adapter.persistence.entity.UsuarioJpaEntity;
import br.com.unip.stan.authserver.domain.Usuario;

public class UserMapper {

	public static Optional<Usuario> maptToDomainEntity(Optional<UsuarioJpaEntity> usuarioJpaEntity) {

		Optional<Usuario> usuario = Optional.empty();

		if (usuarioJpaEntity.isPresent()) {
			usuario = Optional.of(maptToDomainEntity(usuarioJpaEntity.get()));
		}

		return usuario;
	}

	public static Usuario maptToDomainEntity(UsuarioJpaEntity usuarioJpaEntity) {
		return Usuario.builder().id(usuarioJpaEntity.getId()).username(usuarioJpaEntity.getUsername())
				.password(usuarioJpaEntity.getPassword())
				.roles(RoleMapper.maptToDomainEntity(usuarioJpaEntity.getRegrasJpaEntity()))
				.isEnable(usuarioJpaEntity.isEnable()).build();
	}

	public static UsuarioJpaEntity maptToJpaEntity(Usuario usuario) {
		return UsuarioJpaEntity.builder().id(usuario.getId()).username(usuario.getUsername())
				.password(usuario.getPassword()).regrasJpaEntity(RoleMapper.maptToJpaEntity(usuario.getRoles()))
				.isEnable(usuario.isEnable()).build();
	}

}
