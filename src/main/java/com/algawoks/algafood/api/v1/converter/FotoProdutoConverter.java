package com.algawoks.algafood.api.v1.converter;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algawoks.algafood.api.v1.model.output.FotoProdutoOutput;
import com.algawoks.algafood.domain.model.FotoProduto;
import com.algawoks.algafood.domain.model.Produto;

@Component
public class FotoProdutoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProduto toFotoProduto (FotoProdutoInput fotoProdutoInput, Produto produto) {
		
		FotoProduto fotoData = new FotoProduto();
		fotoData.setNomeArquivo(UUID.randomUUID() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename());
		fotoData.setContentType(fotoProdutoInput.getArquivo().getContentType());
		fotoData.setTamanho(fotoProdutoInput.getArquivo().getSize());
		fotoData.setDescricao(fotoProdutoInput.getDescricao());
		fotoData.setProduto(produto);	
		fotoData.setId(produto.getId());

		return fotoData;
	}
	
	public FotoProdutoOutput toFotoProdutoOutput (FotoProduto fotoProduto) {
		FotoProdutoOutput fotoProdutoOut = modelMapper.map(fotoProduto, FotoProdutoOutput.class);
		return fotoProdutoOut;
	}

}














