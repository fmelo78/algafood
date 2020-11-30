package com.algawoks.algafood.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.output.PedidoResumoOutput;
import com.algawoks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoOutput toPedidoResumoOutput (Pedido pedido) {
		PedidoResumoOutput pedidoOut = modelMapper.map(pedido, PedidoResumoOutput.class);		
		return pedidoOut;
	}
	
	public List<PedidoResumoOutput> toPedidoResumoListOutput (List<Pedido> pedidos){
		List<PedidoResumoOutput> pedidosOut = pedidos.stream().map(
				pedido -> toPedidoResumoOutput(pedido)).collect(Collectors.toList());
		return pedidosOut;
	}

}
