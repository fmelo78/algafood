package com.algawoks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.customized.CustomizedRestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements CustomizedRestauranteRepository{
	
	@PersistenceContext
	private EntityManager manager;

//  Modo 1 de criação de consulta - JPQL dinâmico
//	@Override
//	public List<Restaurante> buscarNomeTaxa(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//		var jpql = new StringBuilder();
//		var parametros = new HashMap<String, Object>();
//		jpql.append("from Restaurante where 0=0 ");
//		
//		// Caso o parâmetros seja informado, a query JPQL recebe a cláusula where e o parâmetro do bloco 
//		// condicional é incluído em um MAP para, posteriormente, fazer o bind com o valor recebido na 
//		// requisição e definir o setParameter
//		if (StringUtils.hasLength(nome)) {
//			jpql.append("and nome like :nome ");
//			parametros.put("nome", "%" + nome + "%");
//		}
//		if (taxaFreteInicial != null) {
//			jpql.append("and taxaFrete >= :taxaInicial ");
//			parametros.put("taxaInicial", taxaFreteInicial);
//		}
//		if (taxaFreteFinal != null) {
//			jpql.append("and taxaFrete <= :taxaFinal");
//			parametros.put("taxaFinal", taxaFreteFinal);
//		}
//		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//		
//		// Executa o método setParameter somente com os critérios recebidos na requisição, e, previamente, 
//		// armazenados na coleção HashMap.
//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//		return query.getResultList();
//	}
	
	
	
//  Modo 2 de criação de consulta - Criteria
	@Override
	public List<Restaurante> buscarNomeTaxa(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.hasLength(nome)) {
			Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
			predicates.add(nomePredicate);
		}
		if (taxaFreteInicial != null) {
			Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
			predicates.add(taxaInicialPredicate);
		}
		if (taxaFreteFinal != null) {
			Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
			predicates.add(taxaFinalPredicate);
		}
		// Transformação do ArrayList em Array de objetos do tipo Predicate
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery <Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
}
