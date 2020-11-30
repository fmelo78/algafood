package com.algawoks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.algawoks.algafood.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	
//	Query que busca todos os registros de Pedido, incluindo um join com Pedido.cliente (propriedade cliente
//	da classe Pedido), um join com Pedido.restaurante e outro join com Pedido.restaurante.cozinha
	@Query ("from Pedido p join fetch p.cliente join fetch p.restaurante join fetch p.restaurante.cozinha" )
	List<Pedido> findAll();
	
	public Optional<Pedido> findByCodigo (String codigo);
}
