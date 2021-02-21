package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição de uma foto do produto")
@Data
public class FotoProdutoOutput {
	
	@ApiModelProperty(value = "Nome do arquivo da foto", example = "bce1d741-a0ec-44f7-b406-a7e9238d761f_Academia 2.png")
	private String nomeArquivo;
	
	@ApiModelProperty(value = "Descrição do arquivo da foto", example = "Feijoada completa")
	private String descricao;
	
	@ApiModelProperty(value = "Tipo do arquivo da foto", example = "image/png")
	private String contentType;
	
	@ApiModelProperty(value = "Tamanho do arquivo da foto ", example = "17571")
	private Long tamanho;

}
