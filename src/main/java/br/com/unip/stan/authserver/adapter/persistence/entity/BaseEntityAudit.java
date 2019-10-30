package br.com.unip.stan.authserver.adapter.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntityAudit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@CreatedBy
	private String createdBy;

	@JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
	@JsonIgnore
	@CreatedDate
	private LocalDateTime createdDate;

	@JsonIgnore
	@LastModifiedBy
	private String lastModifiedBy;

	@JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
	@JsonIgnore
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

}