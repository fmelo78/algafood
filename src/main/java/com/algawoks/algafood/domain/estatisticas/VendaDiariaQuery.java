package com.algawoks.algafood.domain.estatisticas;

import java.util.List;

import com.algawoks.algafood.domain.filter.VendaDiariaFilter;
import com.algawoks.algafood.domain.model.estatisticas.VendaDiaria;

public interface VendaDiariaQuery {
	
	public List<VendaDiaria> pesquisarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
