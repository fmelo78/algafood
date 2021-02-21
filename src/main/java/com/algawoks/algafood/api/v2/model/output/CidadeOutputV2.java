package com.algawoks.algafood.api.v2.model.output;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CidadeOutput", description = "Modelo de dados para exibição de uma cidade")
@Getter
@Setter
public class CidadeOutputV2 extends RepresentationModel<CidadeOutputV2> {
	
	@ApiModelProperty(value = "Id da cidade", example = "1")
	private Long idCidade;
	
	@ApiModelProperty(value = "Nome da cidade", example = "São Paulo")
	private String nomeCidade;
	
	@ApiModelProperty(value = "Id do estado", example = "1")
	private Long idEstado;
	
	@ApiModelProperty(value = "Nome do estado", example = "Minas Gerais")
	private String nomeEstado;

}
