package com.algawoks.algafood.domain.service;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

public interface EmailService {
	
	public void enviar(Mensagem mensagem);
	
	@Data
	@Builder
	class Mensagem {
		
		@Singular
		private Set<String> destinatarios;
		
		@NonNull
		private String assunto;
		
		@NonNull
		private String corpo;
	}

}
