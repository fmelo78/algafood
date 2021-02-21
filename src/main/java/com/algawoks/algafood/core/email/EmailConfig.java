package com.algawoks.algafood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algawoks.algafood.domain.service.EmailService;
import com.algawoks.algafood.infrastructure.email.EmailFakeService;
import com.algawoks.algafood.infrastructure.email.EmailSandboxService;
import com.algawoks.algafood.infrastructure.email.EmailSmtpService;

@Configuration
public class EmailConfig {
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EmailService emailService() {
		switch (emailProperties.getImpl()) {
		case SMTP:
			return new EmailSmtpService();
		case SANDBOX:
			return new EmailSandboxService();
		case FAKE:
			return new EmailFakeService();
		default:
			return new EmailFakeService();
		}
	}
}
