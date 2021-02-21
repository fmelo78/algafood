package com.algawoks.algafood.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algawoks.algafood.core.email.EmailProperties;
import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.infrastructure.exception.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class EmailSandboxService implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(emailProperties.getSandbox().getDestinatario());
			helper.setSubject(mensagem.getAssunto() + " SANDBOX");
			helper.setText(corpo, true);		
			mailSender.send(mimeMessage);
		} catch (Exception ex) {
			throw new EmailException("Não foi possível enviar o email", ex);
		}
	}

	@Override
	public String processarTemplate (Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception ex) {
			throw new EmailException("Não foi possível montar o template do email", ex);
		}
	}

}
