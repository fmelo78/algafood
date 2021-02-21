package com.algawoks.algafood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel (value = "PageableModel", description = "Parâmetros de paginação do retorno")
@Getter
@Setter
public class PageableModelOpenApi {
	
	@ApiModelProperty(value = "Número da página (página inicial = 0)", example = "1")
	private int page;
	
	@ApiModelProperty(value = "Quantidade de registros por página", example = "10")
	private int size;
	
	@ApiModelProperty(value = "Nome da propriedade de ordenação", example = "nome,asc")
	private List<String> sort;

}
