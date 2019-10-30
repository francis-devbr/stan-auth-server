package br.com.unip.stan.authserver.adapter.persistence.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
@Entity
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UsuarioJpaEntity extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	private String password;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_regras", 
	joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "regra_id", referencedColumnName = "id"))
	private Collection<RegraJpaEntity> regrasJpaEntity;

	@NotNull
	private boolean isEnable;

}