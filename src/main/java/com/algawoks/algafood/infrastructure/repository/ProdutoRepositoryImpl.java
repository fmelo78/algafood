package com.algawoks.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.model.FotoProduto;
import com.algawoks.algafood.domain.repository.customized.CustomizedProdutoRepository;

@Repository
public class ProdutoRepositoryImpl implements CustomizedProdutoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProduto save(FotoProduto fotoProduto) {
		FotoProduto foto = manager.merge(fotoProduto);
		return foto;
	}

	@Override
	public String buscarNomeArquivo(Long produtoId) {
		String nomeArquivo = null;
		FotoProduto metadados = manager.find(FotoProduto.class, produtoId);
		if (metadados != null) {
			nomeArquivo = metadados.getNomeArquivo();
		}
		return nomeArquivo;
	}

	@Override
	public FotoProduto buscarMetadados(Long produtoId) {
		FotoProduto metadados = manager.find(FotoProduto.class, produtoId);
		return metadados;
	}

	@Transactional
	@Override
	public void removerMetadados(FotoProduto metadados) {
		manager.remove(metadados);
	}
	
}
