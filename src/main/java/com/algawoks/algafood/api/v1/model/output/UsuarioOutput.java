package com.algawoks.algafood.api.v1.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation (collectionRelation = "usuarios")
@ApiModel(description = "Modelo simplificado de dados para exibição de um usuário")
@Getter
@Setter
public class UsuarioOutput extends RepresentationModel<UsuarioOutput>{
	
	@ApiModelProperty(value ="Id do cliente", example = "1")
	private Long id;
	
	@ApiModelProperty(value ="Nome do cliente", example = "João da Silva")
	private String nome;
	
	@ApiModelProperty(value ="Email do cliente", example = "joão@joão.com.br")
	private String email;

}
