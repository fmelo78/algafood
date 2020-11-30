package com.algawoks.algafood.infrastructure.estatisticas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algawoks.algafood.domain.estatisticas.VendaDiariaQuery;
import com.algawoks.algafood.domain.filter.VendaDiariaFilter;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.model.StatusPedido;
import com.algawoks.algafood.domain.model.estatisticas.VendaDiaria;

@Repository
public class VendaDiariaQueryImpl implements VendaDiariaQuery{
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<VendaDiaria> pesquisarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
//		Objetu usado para construir queries, predicates e funções de agregação
		var builder = manager.getCriteriaBuilder();
//		Define o tipo de classe devolvido pela consulta. Não a cláusula from
		var query = builder.createQuery(VendaDiaria.class);
//		Define a classe que será consultada. É a cláusula from
		var root = query.from(Pedido.class);
		
//		Define o array das cláusulas where
		var predicates = new ArrayList<Predicate>();
		if (filtro.getRestauranteId() != null) {
		    predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		if (filtro.getDataInicio() != null) {
		    predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataInicio()));
		}
		if (filtro.getDataFim() != null) {
		    predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataFim()));
		}
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
//		Cria uma expressão que executa a função "convert_tz" (nativa do BD), retornando "Date" 
//		da propriedade "dataCriacao" (objeto root = Pedido)
		var functionTimeZoneConverter = builder.function("convert_tz", Date.class, 
				root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
//		Cria uma expressão que executa a função "date" (nativa do BD), retornando "Date" 
//		do resultado da função functionTimeZoneConverter
		var functionDateDataCriacao = builder.function("date", Date.class, functionTimeZoneConverter);
		
//		Define que o resultado da pesquisa vai chamar o contrutor da classe VendaDiaria
//		passando como argumentos as 3 cláusulas do select (date, int, BigDecimal)
		var selection = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal"))
				);
		
//		Executa o select com o devido agrupamento e retorna a lista de resultados
		query.select(selection);
		query.groupBy(functionDateDataCriacao);
		query.where(predicates.toArray(new Predicate[0]));
		return manager.createQuery(query).getResultList();
	}

}
