package com.algawoks.algafood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedOutputOpenApi <T>{
	
	private List<T> content;
	
	@ApiModelProperty(value = "Quantidade de registros por página", example = "4")
	private Long size;
	
	@ApiModelProperty(value = "Quantidade total de registros", example = "23")
	private Long totalElements;
	
	@ApiModelProperty(value = "Número da página (primeira página = 0)", example = "1")
	private Long page;
	
	@ApiModelProperty(value = "Quantidade total de páginas", example = "6")
	private Long totalPages;

}
