package com.algawoks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algawoks.algafood.domain.model.Produto;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.customized.CustomizedProdutoRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, CustomizedProdutoRepository{
	
	public List<Produto> findTodosByRestaurante (Restaurante restaurante);
	
//	Checar sempre as propriedades da entidade (ativo/restaurante são propriedades de Produto)
	@Query ("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	public List<Produto> findAtivosByRestaurante (Restaurante restaurante);

	@Query ("from Produto p where p.ativo = false and p.restaurante = :restaurante")
	public List<Produto> findInativosByRestaurante (Restaurante restaurante);

	@Query ("from Produto p where restaurante.id = :restauranteId and id = :produtoId")
	public Optional<Produto> findById(Long restauranteId, Long produtoId);
	
}
