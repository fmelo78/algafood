package com.algawoks.algafood.api.exceptionhander;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class Problem {
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private OffsetDateTime timestamp;
	private String userMessage;
	private List<Field> objects;
	
	@Data
	@Builder
	@AllArgsConstructor
	public static class Field {
		private String name;
		private String userMessage;
	}
	
}
