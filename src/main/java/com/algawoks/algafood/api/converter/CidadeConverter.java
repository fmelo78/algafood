package com.algawoks.algafood.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.CidadeInput;
import com.algawoks.algafood.api.model.output.CidadeOutput;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Estado;

@Component
public class CidadeConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeOutput toCidadeOut (Cidade cidade) {
		CidadeOutput cidadeOut = modelMapper.map(cidade, CidadeOutput.class);
		return cidadeOut;
	}
	
	public List<CidadeOutput> toCidadeOutList (List<Cidade> cidades){
		List<CidadeOutput> cidadesOut = new ArrayList<>();
		cidadesOut = cidades.stream().map(cidade -> toCidadeOut(cidade)).collect(Collectors.toList());
		return cidadesOut;		
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
	
}
