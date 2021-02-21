package com.algawoks.algafood.infrastructure.storage;

import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algawoks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algawoks.algafood.core.storage.StorageProperties;
import com.algawoks.algafood.domain.service.FotoStorageService;
import com.algawoks.algafood.infrastructure.exception.StorageException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService{
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void salvarArquivo(FotoProdutoInput fotoProdutoInput, String nomeArquivo) {
		try {
			String bucketName = storageProperties.getS3().getBucket();
			String arquivoPath = String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
			InputStream inputStream = fotoProdutoInput.getArquivo().getInputStream();
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(fotoProdutoInput.getArquivo().getContentType());
			PutObjectRequest object = new PutObjectRequest(bucketName, arquivoPath, inputStream, metadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(object);
		} catch (Exception ex) {
			throw new StorageException("Não foi possível armazenar o arquivo no serviço AWS S3", ex);
		}
	}

	@Override
	public void removerArquivo(String nomeArquivo) {
		try {
			String bucketName = storageProperties.getS3().getBucket();
			String arquivoPath = String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
			amazonS3.deleteObject(bucketName, arquivoPath);
		} catch (Exception ex) {
			throw new StorageException("Não foi possível excuir o arquivo do Amazon S3", ex);
		}
	}

	@Override
	public FotoRetorno recuperarArquivo(String nomeArquivo) {
		String bucketName = storageProperties.getS3().getBucket();
		String arquivoPath = String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
		URL url = amazonS3.getUrl(bucketName, arquivoPath);
		FotoRetorno fotoRetorno = FotoRetorno.builder().url(url.toString()).build();
		return fotoRetorno;
	}

}
