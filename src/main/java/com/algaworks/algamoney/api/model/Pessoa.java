package com.algaworks.algamoney.api.model;

import com.algaworks.algamoney.api.arquiteturabase.entidade.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "pessoa")
@EqualsAndHashCode(callSuper = false)
public @Data  class Pessoa extends Entidade {

	@NotNull
	private String nome;

	@Embedded
	private Endereco endereco;

	@NotNull
	private Boolean ativo;

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}

}
