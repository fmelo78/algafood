package com.algawoks.algafood.domain.listemer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algawoks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.domain.service.EmailService.Mensagem;
import com.algawoks.algafood.infrastructure.exception.EmailException;

@Component
public class PedidoConfirmadoListener {
	
	@Autowired
	private EmailService emailService;
	
//	Sempre que um objeto "PedidoConfirmadoEvent" for criado, esse método será executado
	@TransactionalEventListener (phase = TransactionPhase.AFTER_COMMIT)
	public void enviarEmail (PedidoConfirmadoEvent event) {
		try {
			Pedido pedido = event.getPedido();
			Mensagem mensagem = Mensagem.builder()
					.destinatario(pedido.getCliente().getEmail())
					.assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
					.corpo("pedido-confirmado.html")
					.variavel("pedido", pedido)
					.build();
			emailService.enviar(mensagem);
		} catch (Exception ex) {
			throw new EmailException("Criar tratamento em ExceptionHandler", ex);
		}
	}
		
	@TransactionalEventListener (phase = TransactionPhase.AFTER_COMMIT)
	public void enviarNotaFiscal (PedidoConfirmadoEvent event) {
		System.out.println(String.format("Emitindo NF para o cliente %s", event.getPedido().getCliente().getNome()));
	}

}
