package com.algawoks.algafood.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>>{

	@Override
	public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//		Início do objeto que exibe os metadados da página
		gen.writeStartObject();
//		Metadados da página, propriamente ditos
		gen.writeObjectField("content", page.getContent());
		gen.writeNumberField("size", page.getSize());
		gen.writeNumberField("totalElemeents", page.getTotalElements());
		gen.writeNumberField("page", page.getNumber());
		gen.writeNumberField("totalPages", page.getTotalPages());
//		Fechamento do objeto que exibe os metadados da página
		gen.writeEndObject();
	}
}
