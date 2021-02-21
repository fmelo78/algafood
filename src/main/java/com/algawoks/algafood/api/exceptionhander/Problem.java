package com.algawoks.algafood.api.exceptionhander;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "Problema", description = "Modelo de representação de um problema")
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class Problem {
	@ApiModelProperty(example = "404", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "http://algaworks.com.br/recurso-nao-encontrado", position = 5)
	private String type;
	
	@ApiModelProperty(example = "Recurso não encontrado", position = 10)
	private String title;
	
	@ApiModelProperty(example = "Restaurante de código 9 não encontrado", position = 15)
	private String detail;
	
	@ApiModelProperty(example = "2020-12-08T01:54:39.6592634Z", position = 20)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "Restaurante de código 9 não encontrado", position = 25)
	private String userMessage;
	
	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
	private List<Field> objects;
	
	@ApiModel(value = "ObjetoProblema")
	@Data
	@Builder
	@AllArgsConstructor
	public static class Field {
		
		@ApiModelProperty(example = "preco")
		private String name;
		
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
	}
	
}
