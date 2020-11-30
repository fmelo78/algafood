package com.algawoks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algawoks.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao (valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;
	
	@Embedded
	private Endereco endereco;
	
	@ManyToMany
	// Anotações para definir os nomes da tebela auxiliar e suas colunas 
	@JoinTable (name = "restaurante_forma_pagamento", 
			joinColumns = @JoinColumn (name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn (name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@CreationTimestamp
	@Column (nullable = false)
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column (nullable = false)
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany (mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
	
	private Boolean ativo = true;
	
	private Boolean aberto = true;
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
			joinColumns = @JoinColumn (name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn (name = "usuario_id"))
	private Set<Usuario> usuariosResponsveis = new HashSet<>();
	
	
//	--------------------------------------- MÉTODOS -----------------------------------------
	
	public Boolean aceitaFormaPagamento (FormaPagamento formaPagamento) {
		return this.getFormasPagamento().contains(formaPagamento);
	}
	
	public Boolean possuiProduto (Produto produto) {
		return this.getProdutos().contains(produto);
	}
	
}
