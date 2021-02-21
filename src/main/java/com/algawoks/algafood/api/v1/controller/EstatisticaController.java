package com.algawoks.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.openapi.controller.EstatisticaControllerOpenApi;
import com.algawoks.algafood.domain.estatisticas.VendaDiariaQuery;
import com.algawoks.algafood.domain.estatisticas.VendaDiariaReport;
import com.algawoks.algafood.domain.filter.VendaDiariaFilter;
import com.algawoks.algafood.domain.model.estatisticas.VendaDiaria;

@RestController
@RequestMapping ("/v1/estatisticas")
public class EstatisticaController implements EstatisticaControllerOpenApi{
	
	@Autowired
	private VendaDiariaQuery vendaDiariaQuery;
	
	@Autowired
	private VendaDiariaReport vendaDiariaReport;

	@GetMapping (path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VendaDiaria>> perquisarVendasDiarias (VendaDiariaFilter filtro, 
			@RequestParam (required = false, defaultValue = "+00:00") String timeOffset){
		List<VendaDiaria> vendasDiarias = vendaDiariaQuery.pesquisarVendasDiarias(filtro, timeOffset);
		return ResponseEntity.status(HttpStatus.OK).body(vendasDiarias);
	}
	
	@GetMapping (path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> perquisarVendasDiariasPdf (VendaDiariaFilter filtro, 
			@RequestParam (required = false, defaultValue = "+00:00") String timeOffset){
		
		byte[] bytesPdf = vendaDiariaReport.emitirVendasDiariasPdf(filtro, timeOffset);
		
//		Define que o conteúde deve ser baixado pelo browser, com um nome sugerido
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
}
