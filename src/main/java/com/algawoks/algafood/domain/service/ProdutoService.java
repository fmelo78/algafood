package com.algawoks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.api.v1.converter.ProdutoConverter;
import com.algawoks.algafood.api.v1.model.input.ProdutoInput;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algawoks.algafood.domain.model.Produto;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoConverter produtoConverter;
	
	
	public Produto buscarOuFalhar (Long restauranteId, Long produtoId) {
		Produto produto = produtoRepository.findById(restauranteId, produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(
				String.format("Produto de código %d não encontrado no restaurante %s", produtoId, restauranteId)));
		return produto;
	}
	
	public Produto buscarOuFalhar (Long produtoId) {
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(
				String.format("Produto de código %d não encontrado", produtoId)));
		return produto;
	}
	
	@Transactional
	public Produto salvar (Restaurante restaurante, Produto produto) {
		ProdutoInput produtoIn = produtoConverter.toProdutoInput(produto);
		List<ProdutoInput> produtosIn = produtoConverter.toProdutoListInput(restaurante.getProdutos());
		if (produtosIn.contains(produtoIn)) {
			throw new NegocioException("O produto que está sendo cadastrado já existe nesse restaurante");
		}
		produto.setRestaurante(restaurante);
		Produto produtoT1 = produtoRepository.save(produto);
		return produtoT1;
	}
	
	@Transactional
	public Produto salvar (Produto produto) {
		Produto produtoT1 = produtoRepository.save(produto);
		return produtoT1;
	}

	@Transactional
	public void remover(Long produtoId) {
		produtoRepository.deleteById(produtoId);		
	}

}
