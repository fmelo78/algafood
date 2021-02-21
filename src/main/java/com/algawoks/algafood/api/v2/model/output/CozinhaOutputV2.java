package com.algawoks.algafood.api.v2.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algawoks.algafood.api.v1.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@ApiModel(value = "CozinhaOutput", description = "Modelo de dados para a exibição de uma cozinha")
@Getter
@Setter
public class CozinhaOutputV2 extends RepresentationModel<CozinhaOutputV2>{

	@ApiModelProperty(value = "Id da cozinha", example = "1")
	@JsonView (RestauranteView.Resumo.class)
	private Long idCozinha;
	
	@ApiModelProperty(value = "Nome da cozinha", example = "Tailandesa")
	@JsonView (RestauranteView.Resumo.class)
	private String nomeCozinha;
	
}
