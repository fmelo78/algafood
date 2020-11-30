package com.algawoks.algafood.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.CozinhaInput;
import com.algawoks.algafood.api.model.output.CozinhaOutput;
import com.algawoks.algafood.domain.model.Cozinha;

@Component
public class CozinhaConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaOutput toCozinhaOut (Cozinha cozinha) {
		CozinhaOutput cozinhaOut = modelMapper.map(cozinha, CozinhaOutput.class);
		return cozinhaOut;
	}
	
	public List<CozinhaOutput> toCozinhaOutList (List<Cozinha> cozinhas){
		List<CozinhaOutput> cozinhasOut = new ArrayList<>();
		cozinhasOut = cozinhas.stream().map(cozinha -> toCozinhaOut(cozinha)).collect(Collectors.toList());
		return cozinhasOut;
	}
	
	
	public Cozinha toCozinha (CozinhaInput cozinhaIn) {
		Cozinha cozinha = modelMapper.map(cozinhaIn, Cozinha.class);
		return cozinha;
	}
	
	public void copyToCozinha (CozinhaInput cozinhaIn, Cozinha cozinha) {
		modelMapper.map(cozinhaIn, cozinha);
	}

}
