package com.algawoks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class Endereco {
	
	@Column (name = "end_cep")
	private String cep;
	
	@Column (name = "end_logradouro")
	private String logradouro;
	
	@Column (name = "end_numero")
	private String numero;
	
	@Column (name = "end_complemento")
	private String complemento;
	
	@Column (name = "end_bairro")
	private String bairro;
	
	@JoinColumn (name = "end_cidade_id")
	@ManyToOne (fetch = FetchType.LAZY)
	private Cidade cidade;

}
