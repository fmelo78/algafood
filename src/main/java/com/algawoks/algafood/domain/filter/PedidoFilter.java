package com.algawoks.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dado dos filtros de pesquisa para pedidos")
@Data
public class PedidoFilter {
	
	@ApiModelProperty(value = "Id do cliente", example = "1")
	private Long clienteId;
	
	@ApiModelProperty(value = "Id do restaurante", example = "1")
	private Long restauranteId;
	
	@ApiModelProperty(value = "Data de criação inicial do filtro", example = "2020-12-11T01:12:07Z")
	@DateTimeFormat (iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@ApiModelProperty(value = "Data de criação final do filtro", example = "2020-12-12T01:12:07Z")
	@DateTimeFormat (iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	
}
