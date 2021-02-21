package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.model.input.ProdutoInput;
import com.algawoks.algafood.api.v1.model.output.ProdutoOutput;
import com.algawoks.algafood.domain.model.Produto;

@Component
public class ProdutoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoOutput toProdutoOutput (Produto produto) {
		ProdutoOutput produtoOut = modelMapper.map(produto, ProdutoOutput.class);
		return produtoOut;
	}
	
	public List<ProdutoOutput> toProdutoListOutput (List<Produto> produtos) {
		List<ProdutoOutput> produtosOut = new ArrayList<>();
		produtosOut = produtos.stream().map(produto -> toProdutoOutput(produto)).collect(Collectors.toList());
		return produtosOut;
	}
	
	public Produto toProduto (ProdutoInput produtoIn) {
		Produto produto = modelMapper.map(produtoIn, Produto.class);
		return produto;
	}
	
	public void toCopyProduto (ProdutoInput produtoIn, Produto produto) {
		modelMapper.map(produtoIn, produto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ProdutoInput toProdutoInput (Produto produto) {
		ProdutoInput produtoIn = modelMapper.map(produto, ProdutoInput.class);
		return produtoIn;
	}
	
	public List<ProdutoInput> toProdutoListInput (List<Produto> produtos){
		List<ProdutoInput> produtoListInput = new ArrayList<>();
		produtoListInput = produtos.stream().map((produto) -> toProdutoInput(produto)).collect(Collectors.toList());
		return produtoListInput;
	}

}
