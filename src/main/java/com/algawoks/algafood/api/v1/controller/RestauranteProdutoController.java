package com.algawoks.algafood.api.v1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.ProdutoConverter;
import com.algawoks.algafood.api.v1.model.input.ProdutoInput;
import com.algawoks.algafood.api.v1.model.output.ProdutoOutput;
import com.algawoks.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algawoks.algafood.domain.model.Produto;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.ProdutoRepository;
import com.algawoks.algafood.domain.service.ProdutoService;
import com.algawoks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoConverter produtoConverter;
	
	
	@GetMapping
	public ResponseEntity<List<ProdutoOutput>> listar (@PathVariable Long restauranteId, @RequestParam (required = false) String status){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		if (status == null) status = "";
		
		List<Produto> produtos = new ArrayList<>();
		if (status.equals("ativos")) {
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		else if (status.equals("inativos")) {
			produtos = produtoRepository.findInativosByRestaurante(restaurante);
		}
		else {
			produtos = produtoRepository.findTodosByRestaurante(restaurante);
		}
		
		List<ProdutoOutput> produtosOut = produtoConverter.toProdutoListOutput(produtos);	
		return ResponseEntity.status(HttpStatus.OK).body(produtosOut);
	}
	
//	@GetMapping
//	public ResponseEntity<?> listar (@PathVariable Long restauranteId){
//		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
//		List<Produto> produtos = restaurante.getProdutos();
//		List<ProdutoOutput> produtosOut = produtoConverter.toProdutoListOutput(produtos);		
//		return ResponseEntity.status(HttpStatus.OK).body(produtosOut);
//	}
	
	@GetMapping ("/{produtoId}")
	public ResponseEntity<ProdutoOutput> buscar (@PathVariable Long restauranteId, @PathVariable Long produtoId){
		Produto produto = restauranteService.buscarProdutoRestaurante(restauranteId, produtoId);
		ProdutoOutput produtoOut = produtoConverter.toProdutoOutput(produto);		
		return ResponseEntity.status(HttpStatus.OK).body(produtoOut);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoOutput> adicionar (@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoIn){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		Produto produto = produtoConverter.toProduto(produtoIn);
		Produto produtoT1 = produtoService.salvar(restaurante, produto);
		ProdutoOutput produtoOut = produtoConverter.toProdutoOutput(produtoT1);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoOut);
	}
	
	@PutMapping ("/{produtoId}")
	public ResponseEntity<ProdutoOutput> alterar (@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestBody @Valid ProdutoInput produtoIn){
		Produto produto = restauranteService.buscarProdutoRestaurante(restauranteId, produtoId);
		produtoConverter.toCopyProduto(produtoIn, produto);
		Produto produtoT1 = produtoService.salvar(produto);
		ProdutoOutput produtoOut = produtoConverter.toProdutoOutput(produtoT1);
		return ResponseEntity.status(HttpStatus.OK).body(produtoOut);
	}
	
	@DeleteMapping ("/{produtoId}")
	public ResponseEntity<?> remover (@PathVariable Long restauranteId, @PathVariable Long produtoId){
		restauranteService.buscarProdutoRestaurante(restauranteId, produtoId);
		produtoService.remover(produtoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	
	

}
