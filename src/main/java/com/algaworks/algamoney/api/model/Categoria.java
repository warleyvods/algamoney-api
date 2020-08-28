package com.algaworks.algamoney.api.model;

import com.algaworks.algamoney.api.arquiteturabase.entidade.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "categoria")
@EqualsAndHashCode(callSuper = true)
public class Categoria extends Entidade {

	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

}


