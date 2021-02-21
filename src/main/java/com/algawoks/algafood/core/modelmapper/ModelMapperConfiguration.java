package com.algawoks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algawoks.algafood.api.v1.model.input.ItemPedidoInput;
import com.algawoks.algafood.api.v1.model.output.EnderecoOutput;
import com.algawoks.algafood.api.v2.model.input.CidadeInputV2;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Endereco;
import com.algawoks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
//		modelMappper.createTypeMap(Restaurante.class, RestauranteOutputModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteOutputModel::setPrecoFrete);		
		
		var enderecoMapping1 = modelMapper.createTypeMap(Endereco.class, EnderecoOutput.class);
		enderecoMapping1.addMapping(
				origem -> origem.getCidade().getEstado().getNome(), 
				(destino, valor) -> destino.getCidade().setEstado((String) valor)
				);
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    	.addMappings(mapper -> mapper.skip(ItemPedido::setId));  
		
		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
    		.addMappings(mapper -> mapper.skip(Cidade::setId));  
		
		return modelMapper;		
	}

}