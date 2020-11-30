package com.algawoks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.customized.CustomizedRestauranteRepository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, 
	CustomizedRestauranteRepository, JpaSpecificationExecutor<Restaurante> {
	
//	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
//	List<Restaurante> findAll();
	
	public List<Restaurante> findByTaxaFreteBetween (BigDecimal taxa1, BigDecimal taxa2);
	
	public boolean existsByNomeContaining (String nome);
	
	public int countByCozinhaId (Long cozinhaId);
	
	// Query JPQL com os parâmetros oriundos da assinatura do método
	// O binding dos parâmetros para o JPQL é feito pelo uso de ":"
	// @Query ("from Restaurante where nome like %:nome% and cozinha.id = :id")
	public List<Restaurante> buscarNomeCozinhaId (String nome, @Param("id") Long cozinhaId);
		
}
