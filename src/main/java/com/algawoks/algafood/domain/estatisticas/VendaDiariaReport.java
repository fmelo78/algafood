package com.algawoks.algafood.domain.estatisticas;

import com.algawoks.algafood.domain.filter.VendaDiariaFilter;

public interface VendaDiariaReport {

	public byte[] emitirVendasDiariasPdf (VendaDiariaFilter filtro, String timeOffset);
}
