package com.algawoks.algafood.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.FormaPagamentoInput;
import com.algawoks.algafood.api.model.output.FormaPagamentoOutput;
import com.algawoks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoOutput toFormaPagamentoOutput (FormaPagamento formaPagamento) {
		FormaPagamentoOutput formaPagamentoOut = modelMapper.map(formaPagamento, FormaPagamentoOutput.class);
		return formaPagamentoOut;
	}

	public List<FormaPagamentoOutput> toFormaPagamentoOutputList (List<FormaPagamento> formasPagamento) {
		 List<FormaPagamentoOutput> formasPagamentoOut = formasPagamento.stream()
				 .map(pagamento -> toFormaPagamentoOutput(pagamento)).collect(Collectors.toList());
		return formasPagamentoOut;
	}
	
	public FormaPagamento toFormaPagamento (FormaPagamentoInput formaPagamentoIn) {
		FormaPagamento formaPagamento = modelMapper.map(formaPagamentoIn, FormaPagamento.class);
		return formaPagamento;
	}
	
	public void copyToFormaPagamento (FormaPagamentoInput formaPagamentoIn, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoIn, formaPagamento);
	}
	
	
}
