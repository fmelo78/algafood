package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.model.input.RestauranteInput;
import com.algawoks.algafood.api.v1.model.output.RestauranteOutput;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.model.Restaurante;

@Component
public class RestauranteConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteOutput toRestauranteOut (Restaurante restaurante) {
		RestauranteOutput restauranteOut = modelMapper.map(restaurante, RestauranteOutput.class);
		return restauranteOut;
	}
	
	public List<RestauranteOutput> toRestauranteOutList (List<Restaurante> restaurantes) {
		List<RestauranteOutput> restaurantesOut = new ArrayList<>();
		restaurantesOut = restaurantes.stream().map(restaurante -> toRestauranteOut(restaurante)).collect(Collectors.toList());
		return restaurantesOut;
	}
	
	
	
	public Restaurante toRestaurante (RestauranteInput restauranteIn) {
		Restaurante restaurante = modelMapper.map(restauranteIn, Restaurante.class);
		return restaurante;
	}
	
	public void copyToRestaurante (RestauranteInput restauranteIn, Restaurante restaurante) {
	//  Para evitar o erro | org.hibernate.HibernateException: identifier of an instance of 
	//  com.algawoks.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		modelMapper.map(restauranteIn, restaurante);
	}

}
