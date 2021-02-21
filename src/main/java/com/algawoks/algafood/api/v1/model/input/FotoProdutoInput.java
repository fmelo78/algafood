package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.algawoks.algafood.core.validation.FileContentType;
import com.algawoks.algafood.core.validation.FileSize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para inclusão de uma foto")
@Data
public class FotoProdutoInput {
	
	@ApiModelProperty(value = "Arquivo da foto a ser carregada", hidden = true)
	@FileSize (max = "3MB")
	@FileContentType (allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@NotNull
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto", example = "Feijoada completa", required = true)
	@NotBlank
	private String descricao;
	
}
