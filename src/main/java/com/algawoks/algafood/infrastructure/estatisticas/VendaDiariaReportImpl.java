package com.algawoks.algafood.infrastructure.estatisticas;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algawoks.algafood.domain.estatisticas.VendaDiariaQuery;
import com.algawoks.algafood.domain.estatisticas.VendaDiariaReport;
import com.algawoks.algafood.domain.filter.VendaDiariaFilter;
import com.algawoks.algafood.infrastructure.exception.ReportException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class VendaDiariaReportImpl implements VendaDiariaReport{
	
	@Autowired
	private VendaDiariaQuery vendaDiariaQuery;

	@Override
	public byte[] emitirVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset) {
		
		try {
//			Arquivo no formato .jasper que será utilizado como stream do JasperFillManager
//			O caminho indica a localização do arquivo .jasper, a partir do Classpath
			var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
			
//			Parâmetros que serão utilizados para geração do relatório
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("REPORT_LOCALE",  new Locale("pt", "BR"));
			
//			Realiza a pesquisa que retorna o Json da consulta agregada (dataSource do Report)
			var vendasDiarias = vendaDiariaQuery.pesquisarVendasDiarias(filtro, timeOffset);
//			Cria uma fonte de dados a partir de um objeto de coleção" no Java (Java Bean)
			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
			
//			Gerente de preenchimento de relatórios Jasper
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
		}
		catch (Exception ex){
			throw new ReportException("Não foi possível emitir o relatório de vendas diárias", ex);
		}
	}

}
