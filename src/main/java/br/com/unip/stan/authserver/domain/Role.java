package br.com.unip.stan.authserver.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private long id;

	private String name;

	private Collection<Privilege> privileges;

}
