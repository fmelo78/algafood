package com.algawoks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import com.algawoks.algafood.api.model.input.FotoProdutoInput;

import lombok.Builder;
import lombok.Data;

public interface FotoStorageService {
	
//	default indica que além da assinatura, essa será a implementação do método
//	Trecho de código não utilizado. UUID inserido na camada de conversão
	default String gerarNomeArquivo(String nome) {
		String resposta = UUID.randomUUID() + "_" + nome;
		return resposta;
	};

	public void salvarArquivo(FotoProdutoInput fotoProdutoInput, String nomeArquivo);
	
	public void removerArquivo(String nomeArquivo);
	
	public FotoRetorno recuperarArquivo(String nomeArquivo);
	
	@Data
	@Builder
	class FotoRetorno {
		private InputStream inputStream;
		private String url;
	}
	
}
