package com.algawoks.algafood.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algawoks.algafood.core.email.EmailProperties;
import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.infrastructure.exception.EmailException;

@Service
public class EmailSmtpService implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(mensagem.getCorpo(), true);
			mailSender.send(mimeMessage);
		}
		catch (Exception ex) {
			throw new EmailException("Não foi possível enviar o email", ex);
		}
	}

}
