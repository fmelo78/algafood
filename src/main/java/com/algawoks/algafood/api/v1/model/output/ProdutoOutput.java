package com.algawoks.algafood.api.v1.model.output;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição de um produto")
@Data
public class ProdutoOutput {
	
	@ApiModelProperty(value = "Id do produto", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do produto", example = "Feijoada Completa")
	private String nome;
	
	@ApiModelProperty(value = "Descrição do produto", example = "Feijoada completa com arroz, couve e farofa")
	private String descricao;
	
	@ApiModelProperty(value = "Preço do produto", example = "85.80")
	private BigDecimal preco;
	
	@ApiModelProperty(value = "Disponibilidade do produto no cardápio", example = "true")
	private Boolean ativo;	

}
