package com.algawoks.algafood.api.exceptionhander;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO ("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO ("/entidade-em-uso", "Entidade em uso"),
	SOLICITACAO_INCORRETA ("/erro-de-requisicao", "Erro de requisição"),
	PARAMETRO_INVALIDO ("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA ("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS ("/dados-invalidos", "Dados Inválidos"),
	ACESSO_NEGADO ("/acesso-negado", "Acesso Negado");

	private String uri;
	private String title;
		
	private ProblemType(String path, String title) {
		this.uri = "http://algaworks.com.br" + path;
		this.title = title;
	}
	
}
