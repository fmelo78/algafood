package com.algawoks.algafood.domain.repository.customized;

import com.algawoks.algafood.domain.model.FotoProduto;

public interface CustomizedProdutoRepository {

	public FotoProduto save(FotoProduto fotoProduto);
	
	public String buscarNomeArquivo (Long produtoId);
	
	public FotoProduto buscarMetadados(Long produtoId);
	
	public void removerMetadados(FotoProduto metadados);
	
}
