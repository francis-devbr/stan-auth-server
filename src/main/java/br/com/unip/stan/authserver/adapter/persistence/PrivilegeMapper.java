package br.com.unip.stan.authserver.adapter.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.unip.stan.authserver.adapter.persistence.entity.PrivilegioJpaEntity;
import br.com.unip.stan.authserver.domain.Privilege;

public class PrivilegeMapper {

	public static Optional<Privilege> maptToDomainEntity(Optional<PrivilegioJpaEntity> privilegeJpaEntity) {

		Optional<Privilege> privilegio = Optional.empty();

		if (privilegeJpaEntity.isPresent()) {
			privilegio = Optional.of(maptToDomainEntity(privilegeJpaEntity.get()));
		}

		return privilegio;
	}

	public static List<Privilege> maptToDomainEntity(Collection<PrivilegioJpaEntity> privilegesJpaEntity) {
		return privilegesJpaEntity.stream().map(PrivilegeMapper::maptToDomainEntity).collect(Collectors.toList());
	}

	public static List<PrivilegioJpaEntity> maptToJpaEntity(Collection<Privilege> privileges) {
		return privileges.stream().map(PrivilegeMapper::maptToJpaEntity).collect(Collectors.toList());
	}

	public static Privilege maptToDomainEntity(PrivilegioJpaEntity privilegeJpaEntity) {
		return Privilege.builder().id(privilegeJpaEntity.getId()).name(privilegeJpaEntity.getNome()).build();
	}

	public static PrivilegioJpaEntity maptToJpaEntity(Privilege privilege) {
		return PrivilegioJpaEntity.builder().id(privilege.getId()).nome(privilege.getName()).build();
	}

}
