package com.algawoks.algafood.infrastructure.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.algawoks.algafood.api.model.input.FotoProdutoInput;
import com.algawoks.algafood.core.storage.StorageProperties;
import com.algawoks.algafood.domain.service.FotoStorageService;
import com.algawoks.algafood.infrastructure.exception.StorageException;

public class LocalFotoStorageService implements FotoStorageService{
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void salvarArquivo(FotoProdutoInput fotoProdutoInput, String nomeArquivo) {
		try {
			String diretorio = storageProperties.getLocal().getDiretorioFotos();
			Path arquivoPath = Path.of(diretorio, nomeArquivo);
			fotoProdutoInput.getArquivo().transferTo(arquivoPath);
		} catch (Exception ex) {
			throw new StorageException("Não foi possível armazenar o arquivo", ex);
		} 
	}

	@Override
	public void removerArquivo(String nomeArquivo) {
		try {
			String diretorio = storageProperties.getLocal().getDiretorioFotos();
			Path arquivoPath = Path.of(diretorio, nomeArquivo);
			Files.deleteIfExists(arquivoPath);
		} catch (Exception ex) {
			throw new StorageException("Não foi possível remover o arquivo. Pode estar aberto em outro programa.", ex);
		}
	}

	@Override
	public FotoRetorno recuperarArquivo(String nomeArquivo) {
		try {
			String diretorio = storageProperties.getLocal().getDiretorioFotos();
			Path arquivoPath = Path.of(diretorio, nomeArquivo);
			InputStream arquivo = Files.newInputStream(arquivoPath);
			FotoRetorno fotoRetorno = FotoRetorno.builder().inputStream(arquivo).build();
			return fotoRetorno;
		} catch (Exception ex) {
			throw new StorageException("Não foi possível recuperar o arquivo.", ex);
		}
	}

}
