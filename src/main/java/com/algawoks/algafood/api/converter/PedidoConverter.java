package com.algawoks.algafood.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.PedidoInput;
import com.algawoks.algafood.api.model.output.PedidoOutput;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.model.Restaurante;

@Component
public class PedidoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoOutput toPedidoOutput (Pedido pedido) {
		PedidoOutput pedidoOut = modelMapper.map(pedido, PedidoOutput.class);		
		return pedidoOut;
	}
	
	public List<PedidoOutput> toPedidoListOutput (List<Pedido> pedidos){
		List<PedidoOutput> pedidosOut = pedidos.stream().map(pedido -> toPedidoOutput(pedido)).collect(Collectors.toList());
		return pedidosOut;
	}
	
	public Pedido toPedido (PedidoInput pedidoIn) {
		Pedido pedido = modelMapper.map(pedidoIn, Pedido.class);
		return pedido;
	}
	
	public void copyToPedido (PedidoInput pedidoIn, Pedido pedido) {
		modelMapper.map(pedidoIn, pedido);
	}
	
//	Verificar para ver se esse mapeamento funciona.
	public Pedido FromRestauranteToPedido (Restaurante restaurante) {
		Pedido pedido = modelMapper.map(restaurante, Pedido.class);
		return pedido;
	}

}
