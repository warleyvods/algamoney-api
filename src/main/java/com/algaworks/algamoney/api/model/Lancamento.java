package com.algaworks.algamoney.api.model;

import com.algaworks.algamoney.api.arquiteturabase.entidade.Entidade;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lancamento")
public class Lancamento extends Entidade {

	private String descricao;
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private String observacao;

	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;

	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;

}
