package com.algawoks.algafood.api.v1.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@ApiModel(description = "Modelo de dados para exibição de um estado")
@Getter
@Setter
public class EstadoOutput extends RepresentationModel<EstadoOutput>{
	
	@ApiModelProperty(value = "Id do estado", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do estado", example = "São Paulo")
	private String nome;

}
