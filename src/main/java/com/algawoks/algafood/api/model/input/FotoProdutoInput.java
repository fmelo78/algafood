package com.algawoks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.algawoks.algafood.core.validation.FileContentType;
import com.algawoks.algafood.core.validation.FileSize;

import lombok.Data;

@Data
public class FotoProdutoInput {
	
	@FileSize (max = "3MB")
	@FileContentType (allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@NotNull
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
}
