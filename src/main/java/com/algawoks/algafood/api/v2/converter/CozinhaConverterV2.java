package com.algawoks.algafood.api.v2.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v2.controller.CozinhaControllerV2;
import com.algawoks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algawoks.algafood.api.v2.model.output.CozinhaOutputV2;
import com.algawoks.algafood.domain.model.Cozinha;

@Component
public class CozinhaConverterV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOutputV2>{

	@Autowired
	private ModelMapper modelMapper;
	
	
	public CozinhaConverterV2() {
		super(CozinhaControllerV2.class, CozinhaOutputV2.class);
	}

	public CozinhaOutputV2 toCozinhaOut (Cozinha cozinha) {
		CozinhaOutputV2 cozinhaOut = modelMapper.map(cozinha, CozinhaOutputV2.class);
		cozinhaOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CozinhaControllerV2.class).buscar(cozinhaOut.getIdCozinha()))
				.withRel(IanaLinkRelations.SELF));
		cozinhaOut.add(WebMvcLinkBuilder.linkTo(CozinhaControllerV2.class).withRel(IanaLinkRelations.COLLECTION));
		return cozinhaOut;
	}
	
	public List<CozinhaOutputV2> toCozinhaOutList (List<Cozinha> cozinhas){
		List<CozinhaOutputV2> cozinhasOut = new ArrayList<>();
		cozinhasOut = cozinhas.stream().map(cozinha -> toCozinhaOut(cozinha)).collect(Collectors.toList());
		return cozinhasOut;
	}
	
	
	public Cozinha toCozinha (CozinhaInputV2 cozinhaIn) {
		Cozinha cozinha = modelMapper.map(cozinhaIn, Cozinha.class);
		return cozinha;
	}
	
	public void copyToCozinha (CozinhaInputV2 cozinhaIn, Cozinha cozinha) {
		modelMapper.map(cozinhaIn, cozinha);
	}

	@Override
	public CozinhaOutputV2 toModel(Cozinha entity) {
		return toCozinhaOut(entity);
	}

}
