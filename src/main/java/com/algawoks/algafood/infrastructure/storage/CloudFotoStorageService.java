package com.algawoks.algafood.infrastructure.storage;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.algawoks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algawoks.algafood.infrastructure.exception.StorageException;

@Service
public class CloudFotoStorageService {

	@Value ("${algafood.storage.local.diretorio-fotos}")
	private String diretorio;

//	@Override
	public void salvarArquivo(FotoProdutoInput fotoProdutoInput) {
				
		try {
			String nomeArquivo = fotoProdutoInput.getArquivo().getOriginalFilename();
			Path arquivoPath = Path.of(diretorio,  nomeArquivo);
			fotoProdutoInput.getArquivo().transferTo(arquivoPath);
		} catch (Exception ex) {
			throw new StorageException("Não foi possível armazenar o arquivo", ex);
		} 
		
	}

}
