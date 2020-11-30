package com.algawoks.algafood.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.GrupoInput;
import com.algawoks.algafood.api.model.output.GrupoOutput;
import com.algawoks.algafood.domain.model.Grupo;

@Component
public class GrupoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoOutput toGrupoOutput (Grupo grupo) {
		GrupoOutput grupoOut = modelMapper.map(grupo, GrupoOutput.class);
		return grupoOut;
	}
	
	public List<GrupoOutput> toGrupoListOutput (List<Grupo> grupos){
		List<GrupoOutput> gruposOut = new ArrayList<>();
		gruposOut = grupos.stream().map(grupo -> toGrupoOutput(grupo)).collect(Collectors.toList());
		return gruposOut;
	}
	
	public Grupo toGrupo (GrupoInput grupoIn) {
		Grupo grupo = modelMapper.map(grupoIn, Grupo.class);
		return grupo;
	}
	
	public void copyToGrupo (GrupoInput grupoIn, Grupo grupo) {
		modelMapper.map(grupoIn, grupo);
	}

}
