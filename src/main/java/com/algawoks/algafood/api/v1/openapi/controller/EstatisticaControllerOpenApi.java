package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algawoks.algafood.domain.filter.VendaDiariaFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatística")
public interface EstatisticaControllerOpenApi {
	
	@ApiOperation(value = "Consulta a estatística das vendas diárias")
//	A decisão por se utilizar a anotação @Implicit em vez de parametrizar a classe VendaDiariaFilter se deve ao fato 
//	da classe pertencer a um pacote de domínio (por organização, não se utiliza anotações Swagger nessas classes)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "Id do restaurante", example = "1", dataType = "int"),
		@ApiImplicitParam(name = "dataInicio", value = "Data/Hora Inicial do filtro", example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(name = "dataFim", value = "Data/Hora Final do filtro", example = "2019-12-01T00:00:00Z", dataType = "date-time")
	})
	public ResponseEntity<?> perquisarVendasDiarias (
			VendaDiariaFilter filtro, 
			@ApiParam(value = "Deslocamento de horário a ser aplicado", example = "-03:00", defaultValue = "+00:00") String timeOffset);
	
//	Este endpoint não é especificado na documentação, pois é idêntico ao definido acima.
//	Sua implementação, na classe Controller varia apenas pelo MediaType produzido (JSON/PDF)
	public ResponseEntity<?> perquisarVendasDiariasPdf (
			VendaDiariaFilter filtro, 
			String timeOffset);
	
}
