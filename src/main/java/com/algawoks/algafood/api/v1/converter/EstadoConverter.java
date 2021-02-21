package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.controller.EstadoController;
import com.algawoks.algafood.api.v1.model.input.EstadoInput;
import com.algawoks.algafood.api.v1.model.output.EstadoOutput;
import com.algawoks.algafood.domain.model.Estado;

@Component
public class EstadoConverter extends RepresentationModelAssemblerSupport<Estado, EstadoOutput>{
	
	public EstadoConverter() {
		super(EstadoController.class, EstadoOutput.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoOutput toEstadoOut (Estado estado) {
		EstadoOutput estadoOut = modelMapper.map(estado, EstadoOutput.class);
		estadoOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(estadoOut.getId()))
				.withRel(IanaLinkRelations.SELF));
		estadoOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(EstadoController.class).listar())
				.withRel(IanaLinkRelations.COLLECTION));
		return estadoOut;
	}
	
	public CollectionModel<EstadoOutput> toEstadoOutList (List<Estado> estados) {
		List<EstadoOutput> estadosOut = new ArrayList<>();
		estadosOut = estados.stream().map(estado -> toEstadoOut(estado)).collect(Collectors.toList());
		CollectionModel<EstadoOutput> collectionOut = CollectionModel.of(estadosOut);
		collectionOut.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withRel(IanaLinkRelations.SELF));
		return collectionOut;
	}
	
	
	public Estado toEstado (EstadoInput estadoIn) {
		Estado estado = modelMapper.map(estadoIn, Estado.class);
		return estado;
	}
	
	public void copyToEstado (EstadoInput estadoIn, Estado estado) {
		modelMapper.map(estadoIn, estado);
	}

	
	@Override
	public EstadoOutput toModel(Estado entity) {
		return null;
	}

}
