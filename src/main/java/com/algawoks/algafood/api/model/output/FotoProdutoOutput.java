package com.algawoks.algafood.api.model.output;

import lombok.Data;

@Data
public class FotoProdutoOutput {
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

}
