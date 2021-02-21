package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.controller.CozinhaController;
import com.algawoks.algafood.api.v1.model.input.CozinhaInput;
import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;
import com.algawoks.algafood.domain.model.Cozinha;

@Component
public class CozinhaConverter extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOutput>{

	@Autowired
	private ModelMapper modelMapper;
	
	
	public CozinhaConverter() {
		super(CozinhaController.class, CozinhaOutput.class);
	}

	public CozinhaOutput toCozinhaOut (Cozinha cozinha) {
		CozinhaOutput cozinhaOut = modelMapper.map(cozinha, CozinhaOutput.class);
		cozinhaOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CozinhaController.class).buscar(cozinhaOut.getId()))
				.withRel(IanaLinkRelations.SELF));
		cozinhaOut.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(IanaLinkRelations.COLLECTION));
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

	@Override
	public CozinhaOutput toModel(Cozinha entity) {
		return toCozinhaOut(entity);
	}

}
