package com.algawoks.algafood.api.v1.openapi.model;

import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CozinhasOutput", description = "Modelo de dados para paginação de cozinhas")
public class CozinhasOutputOpenApi extends PagedOutputOpenApi<CozinhaOutput>{
	
}
