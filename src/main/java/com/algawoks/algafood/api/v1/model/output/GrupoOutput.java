package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GrupoOutput", description = "Representação de um grupo")
@Data
public class GrupoOutput {
	
	@ApiModelProperty(value = "Id do grupo", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do grupo", example = "Gerentes")
	private String nome;
}
