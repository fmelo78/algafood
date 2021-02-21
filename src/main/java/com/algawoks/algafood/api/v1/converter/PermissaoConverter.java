package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.model.output.PermissaoOutput;
import com.algawoks.algafood.domain.model.Permissao;

@Component
public class PermissaoConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoOutput toPermissaoOutput (Permissao permissao) {
		PermissaoOutput permissaoOut = modelMapper.map(permissao, PermissaoOutput.class);
		return permissaoOut ;
	}
	
	public List<PermissaoOutput> toPermissaoListOutput (List<Permissao> permissoes){
		List<PermissaoOutput> permissoesOut = new ArrayList<>();
		permissoesOut = permissoes.stream().map(permissao -> toPermissaoOutput(permissao)).collect(Collectors.toList());
		return permissoesOut;
	}
	
	
	
	

}
