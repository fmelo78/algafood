package com.algawoks.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioSemSenhaInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
}
