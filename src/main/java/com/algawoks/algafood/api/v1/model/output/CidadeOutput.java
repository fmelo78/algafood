package com.algawoks.algafood.api.v1.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeOutput extends RepresentationModel<CidadeOutput> {
	
	@ApiModelProperty(value = "Id da cidade", example = "1")
	private Long id;
	
	@ApiModelProperty(example = "São Paulo")
	private String nome;
	
	private EstadoOutput estado;

}
