package br.com.x.erp.authorization.usecase.port.out;

import br.com.x.erp.authorization.adapter.persistence.entity.Usuario;

public interface LoginPort {

	Usuario findByUsername(String username);

}
