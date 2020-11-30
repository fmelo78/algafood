package com.algawoks.algafood.core.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

	public Local local = new Local();
	public S3 s3 = new S3();
	public TipoStorage tipo = TipoStorage.LOCAL;
	
	@Data
	public class Local {
		private String diretorioFotos;
	}
	
	@Data
	public class S3 {
		private String chaveAcesso;
		private String chaveSecreta;
		private String bucket;
		private String regiao;
		private String diretorioFotos;
	}
	
	public enum TipoStorage {
		S3, LOCAL;
	}
	
}
