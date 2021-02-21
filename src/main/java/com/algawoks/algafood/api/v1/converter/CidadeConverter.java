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

import com.algawoks.algafood.api.v1.controller.CidadeController;
import com.algawoks.algafood.api.v1.controller.EstadoController;
import com.algawoks.algafood.api.v1.model.input.CidadeInput;
import com.algawoks.algafood.api.v1.model.output.CidadeOutput;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Estado;

@SuppressWarnings(value = "deprecation")
@Component
public class CidadeConverter extends RepresentationModelAssemblerSupport<Cidade, CidadeOutput>{
	
	@Autowired
	private ModelMapper modelMapper;

	
	public CidadeConverter() {
		super(CidadeController.class, CidadeOutput.class);
	}
	
	
	public CidadeOutput toCidadeOut (Cidade cidade) {
		CidadeOutput cidadeOut = modelMapper.map(cidade, CidadeOutput.class);
		
//		cidadeOut.add(Link.of("http://api.algafood.local:8080/cidades/1", IanaLinkRelations.SELF));
//		cidadeOut.add(Link.of("http://api.algafood.local:8080/cidades", IanaLinkRelations.COLLECTION));
//		cidadeOut.getEstado().add(Link.of("http://api.algafood.local:8080/estados/1"));
		
//		cidadeOut.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeOut.getId()).withRel(IanaLinkRelations.SELF));
//		cidadeOut.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel(IanaLinkRelations.COLLECTION));
//		cidadeOut.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeOut.getEstado().getId()).withRel(IanaLinkRelations.SELF));
		
		cidadeOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeOut.getId()))
				.withRel(IanaLinkRelations.SELF));
		cidadeOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
				.withRel(IanaLinkRelations.COLLECTION));
		cidadeOut.getEstado().add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeOut.getEstado().getId()))
				.withRel(IanaLinkRelations.SELF));
		
		return cidadeOut;
	}
	
	public CollectionModel<CidadeOutput> toCidadeOutList (List<Cidade> cidades){
		List<CidadeOutput> cidadesOut = new ArrayList<>();
		cidadesOut = cidades.stream().map(cidade -> toCidadeOut(cidade)).collect(Collectors.toList());
		CollectionModel<CidadeOutput> collectionOut = CollectionModel.of(cidadesOut);
		collectionOut.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar()).withSelfRel());
		return collectionOut;		
	}
	
	
	public Cidade toCidade (CidadeInput cidadeIn) {
		Cidade cidade = modelMapper.map(cidadeIn, Cidade.class);
		return cidade;
	}
	
	public void copyToCidade (CidadeInput cidadeIn, Cidade cidade) {
	//  Para evitar o erro | org.hibernate.HibernateException: identifier of an instance of 
	//  com.algawoks.algafood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeIn, cidade);
	}

	@Override
	public CidadeOutput toModel(Cidade entity) {
		return null;
//		return toCidadeOut(entity);
	}
	
	@Override
	public CollectionModel<CidadeOutput> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}
	
}
