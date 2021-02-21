package com.algawoks.algafood.api.v2.converter;

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

import com.algawoks.algafood.api.v2.controller.CidadeControllerV2;
import com.algawoks.algafood.api.v2.model.input.CidadeInputV2;
import com.algawoks.algafood.api.v2.model.output.CidadeOutputV2;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Estado;

@Component
public class CidadeConverterV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeOutputV2>{
	
	@Autowired
	private ModelMapper modelMapper;

	
	public CidadeConverterV2() {
		super(CidadeControllerV2.class, CidadeOutputV2.class);
	}
	
	public CidadeOutputV2 toCidadeOut (Cidade cidade) {
		CidadeOutputV2 cidadeOut = modelMapper.map(cidade, CidadeOutputV2.class);
		
//		cidadeOut.add(Link.of("http://api.algafood.local:8080/cidades/1", IanaLinkRelations.SELF));
//		cidadeOut.add(Link.of("http://api.algafood.local:8080/cidades", IanaLinkRelations.COLLECTION));
//		cidadeOut.getEstado().add(Link.of("http://api.algafood.local:8080/estados/1"));
		
//		cidadeOut.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeOut.getId()).withRel(IanaLinkRelations.SELF));
//		cidadeOut.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel(IanaLinkRelations.COLLECTION));
//		cidadeOut.getEstado().add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeOut.getEstado().getId()).withRel(IanaLinkRelations.SELF));
		
		cidadeOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CidadeControllerV2.class).buscar(cidadeOut.getIdCidade()))
				.withRel(IanaLinkRelations.SELF));
		cidadeOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(CidadeControllerV2.class).listar())
				.withRel(IanaLinkRelations.COLLECTION));
		
		return cidadeOut;
	}
	
	public List<CidadeOutputV2> toCidadeOutList (List<Cidade> cidades){
		List<CidadeOutputV2> cidadesOut = new ArrayList<>();
		cidadesOut = cidades.stream().map(cidade -> toCidadeOut(cidade)).collect(Collectors.toList());
		return cidadesOut;		
	}
	
	
	public Cidade toCidade (CidadeInputV2 cidadeIn) {
		Cidade cidade = modelMapper.map(cidadeIn, Cidade.class);
		return cidade;
	}
	
	public void copyToCidade (CidadeInputV2 cidadeIn, Cidade cidade) {
	//  Para evitar o erro | org.hibernate.HibernateException: identifier of an instance of 
	//  com.algawoks.algafood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		modelMapper.map(cidadeIn, cidade);
	}

	@Override
	public CidadeOutputV2 toModel(Cidade entity) {
		return null;
//		return toCidadeOut(entity);
	}
	
	@Override
	public CollectionModel<CidadeOutputV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(CidadeControllerV2.class).withSelfRel());
	}
	
}
