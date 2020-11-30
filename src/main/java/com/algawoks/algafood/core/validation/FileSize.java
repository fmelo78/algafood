package com.algawoks.algafood.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
// Classe que implementa a validação
@Constraint(validatedBy = { FileSizeValidator.class })
public @interface FileSize {
	
//	Criar a chave “multiplo.invalido” em messages.properties para customizar mensagem
	String message() default "{fileSize.invalido}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
//	Parâmetro que será recebido como argumento da anotação
	String max();

}
