package br.com.unip.stan.authserver.adapter.persistence;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.unip.stan.authserver.adapter.persistence.entity.RegraJpaEntity;
import br.com.unip.stan.authserver.domain.Role;

public class RoleMapper {
	
	public static Optional<Role> maptToDomainEntity(Optional<RegraJpaEntity> roleJpaEntity) {
		
		Optional<Role> role = Optional.empty();

		if (roleJpaEntity.isPresent()) {
			role = Optional.of(maptToDomainEntity(roleJpaEntity.get()));
		}

		return role;
	}
	
	public static Collection<Role> maptToDomainEntity(Collection<RegraJpaEntity> rolesJpaEntity) {
		return rolesJpaEntity.stream()
				.map(RoleMapper::maptToDomainEntity)
				.collect(Collectors.toList());
	}

	public static Collection<RegraJpaEntity> maptToJpaEntity(Collection<Role> roles) {
		return roles.stream()
				.map(RoleMapper::maptToJpaEntity)
				.collect(Collectors.toList());
	}
	
	public static Role maptToDomainEntity(RegraJpaEntity roleJpaEntity) {
		return Role.builder()
				.id(roleJpaEntity.getId())
				.name(roleJpaEntity.getNome())
				.privileges(PrivilegeMapper.maptToDomainEntity(roleJpaEntity.getPrivilegiosJpaEntity()))
				.build();
	}

	public static RegraJpaEntity maptToJpaEntity(Role role) {
		return RegraJpaEntity.builder()
				.id(role.getId())
				.nome(role.getName())
				.privilegiosJpaEntity(PrivilegeMapper.maptToJpaEntity(role.getPrivileges()))
				.build();
	}
}
