package com.algawoks.algafood.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.EstadoInput;
import com.algawoks.algafood.api.model.output.EstadoOutput;
import com.algawoks.algafood.domain.model.Estado;

@Component
public class EstadoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoOutput toEstadoOut (Estado estado) {
		EstadoOutput estadoOut = modelMapper.map(estado, EstadoOutput.class);
		return estadoOut;
	}
	
	public List<EstadoOutput> toEstadoOutList (List<Estado> estados) {
		List<EstadoOutput> estadosOut = new ArrayList<>();
		estadosOut = estados.stream().map(estado -> toEstadoOut(estado)).collect(Collectors.toList());
		return estadosOut;
	}
	
	
	public Estado toEstado (EstadoInput estadoIn) {
		Estado estado = modelMapper.map(estadoIn, Estado.class);
		return estado;
	}
	
	public void copyToEstado (EstadoInput estadoIn, Estado estado) {
		modelMapper.map(estadoIn, estado);
	}

}
