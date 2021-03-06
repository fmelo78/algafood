package com.algawoks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.PedidoConverter;
import com.algawoks.algafood.api.v1.converter.PedidoResumoConverter;
import com.algawoks.algafood.api.v1.model.input.PedidoInput;
import com.algawoks.algafood.api.v1.model.output.PedidoOutput;
import com.algawoks.algafood.api.v1.model.output.PedidoResumoOutput;
import com.algawoks.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.algawoks.algafood.core.data.PageableTranslator;
import com.algawoks.algafood.core.security.AlgaSecurity;
import com.algawoks.algafood.domain.filter.PedidoFilter;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.repository.PedidoRepository;
import com.algawoks.algafood.domain.service.PedidoService;
import com.algawoks.algafood.infrastructure.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping (path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi{
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoConverter pedidoConverter;
	
	@Autowired
	private PedidoResumoConverter pedidoResumoConverter;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
//	@GetMapping
//	Apenas definindo o parâmetro, o Spring já instancia o PedidoFilter e faz binding
//	com os parâmetros de URL passados pelo usuário
//	public ResponseEntity<?> pesquisar(PedidoFilter filtro){
//		List<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
//		List<PedidoResumoOutput> pedidosOut = pedidoResumoConverter.toPedidoResumoListOutput(pedidos);
//		return ResponseEntity.status(HttpStatus.OK).body(pedidosOut);
//	}
	
//	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
//	Apenas definindo o parâmetro, o Spring já instancia o PedidoFilter e faz binding
//	com os parâmetros de URL passados pelo usuário
	public ResponseEntity<Page<PedidoResumoOutput>> pesquisar(PedidoFilter filtro, @PageableDefault(size=2) Pageable pageable){
		pageable = traduzirPageable(pageable);
		Page<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
		List<PedidoResumoOutput> pedidosListOut = pedidoResumoConverter.toPedidoResumoListOutput(pedidos.getContent());
		Page<PedidoResumoOutput> pedidosPageOut = new PageImpl<>(pedidosListOut, pageable, pedidos.getTotalElements());
		return ResponseEntity.status(HttpStatus.OK).body(pedidosPageOut);
//		return pedidosPageOut;
	}
	
	@GetMapping ("/{codigoPedido}")
	public ResponseEntity<PedidoOutput> buscar (@PathVariable String codigoPedido){
		Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
		PedidoOutput pedidoOut = pedidoConverter.toPedidoOutput(pedido);
		return ResponseEntity.status(HttpStatus.OK).body(pedidoOut);
	}
	
	@PostMapping
	public ResponseEntity<PedidoOutput> adicionar (@RequestBody @Valid PedidoInput pedidoIn){
		Pedido novoPedido = pedidoConverter.toPedido(pedidoIn);

		novoPedido.setCliente(new Usuario());
		Long clienteId = algaSecurity.getUserId();
		novoPedido.getCliente().setId(clienteId);
				
		Pedido pedidoT1 = pedidoService.emitir(novoPedido);
		PedidoOutput pedidoOut = pedidoConverter.toPedidoOutput(pedidoT1);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoOut);
	}
	
	@PutMapping ("/{codigoPedido}/confirmacao")
	public ResponseEntity<?> confirmar (@PathVariable String codigoPedido){
		pedidoService.confirmar(codigoPedido);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping ("/{codigoPedido}/cancelamento")
	public ResponseEntity<?> cancelar (@PathVariable String codigoPedido){
		pedidoService.cancelar(codigoPedido);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping ("/{codigoPedido}/entrega")
	public ResponseEntity<?> entregar (@PathVariable String codigoPedido){
		pedidoService.entregar(codigoPedido);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private Pageable traduzirPageable (Pageable pageable) {
//		Mapeamento: valor inserido pelo cliente / propriedade na ENTIDADE "Pedido"
//		Somente as propriedades listadas no mapeamento serão passíveis de ordenação
		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"clienteNome", "nome.cliente",
				"cliente.nome", "cliente.nome",
				"valorTotal", "valorTotal"
			);
//		Gera um novo Pageable com os critérios de ordenação ajustados no mapeamento
		Pageable pageableT1 = PageableTranslator.translate(pageable, mapeamento);
						
		return pageableT1;
	}
	
}
