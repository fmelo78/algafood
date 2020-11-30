package com.algawoks.algafood.domain.repository.customized;

import java.math.BigDecimal;
import java.util.List;

import com.algawoks.algafood.domain.model.Restaurante;

public interface CustomizedRestauranteRepository {

	List<Restaurante> buscarNomeTaxa (String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	
}
