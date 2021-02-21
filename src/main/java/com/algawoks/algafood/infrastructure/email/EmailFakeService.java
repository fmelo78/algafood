package com.algawoks.algafood.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.infrastructure.exception.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailFakeService implements EmailService{
	
	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void enviar(Mensagem mensagem) {
//		String corpo = processarTemplate(mensagem);
		log.info("\n[FAKE E-MAIL] Para: {}\nRestaurante: {}\nTemplate utilizado: {}", mensagem.getDestinatarios(), mensagem.getAssunto(), mensagem.getCorpo());
	}

	public String processarTemplate (Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception ex) {
			throw new EmailException("Não foi possível montar o template do email", ex);
		}
	}

}
