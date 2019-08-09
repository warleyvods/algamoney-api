package com.algaworks.algamoney.api.model;

import com.algaworks.algamoney.api.arquiteturabase.entidade.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa extends Entidade {

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
