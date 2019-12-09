package br.com.unip.stan.authserver.usecase.port.out;

import br.com.unip.stan.authserver.adapter.persistence.entity.Usuario;

public interface LoginPort {

	Usuario findByUsername(String username);

}
