package br.com.unip.stan.authserver.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Privilege {

	private long id;

	private String name;

}
