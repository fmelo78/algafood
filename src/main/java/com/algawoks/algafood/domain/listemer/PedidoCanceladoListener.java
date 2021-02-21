package com.algawoks.algafood.domain.listemer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algawoks.algafood.domain.event.PedidoCanceladoEvent;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.domain.service.EmailService.Mensagem;

@Component
public class PedidoCanceladoListener {
	
	@Autowired
	private EmailService emailService;
	
	@TransactionalEventListener (phase = TransactionPhase.AFTER_COMMIT)
	public void enviarEmail(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		Mensagem mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido Cancelado")
				.destinatario(pedido.getCliente().getEmail())
				.corpo("pedido-cancelado.html")
				.variavel("pedido", pedido)
				.build();
		emailService.enviar(mensagem);		
	}

}
