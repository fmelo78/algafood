package com.algawoks.algafood.infrastructure.spec;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.algawoks.algafood.domain.model.Restaurante;

public class RestauranteSpecs {
	
	//Implementação com classes aninhadas
	public static Specification<Restaurante> freteGratis() {
		
		return new Specification<Restaurante>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
	        public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate p1 = builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
				return p1;
				
			}
		};
	}
	
	// Implementação com função Lambda
	public static Specification<Restaurante> nomeSemelhante (String nome){
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
}
