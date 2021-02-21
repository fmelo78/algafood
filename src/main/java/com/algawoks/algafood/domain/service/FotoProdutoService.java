package com.algawoks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algawoks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algawoks.algafood.domain.model.FotoProduto;
import com.algawoks.algafood.domain.repository.ProdutoRepository;
import com.algawoks.algafood.domain.service.FotoStorageService.FotoRetorno;
import com.algawoks.algafood.infrastructure.exception.FotoNaoEncontradaException;

@Service
public class FotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvarFoto(FotoProduto metadados, FotoProdutoInput fotoProdutoInput) {
		
		String nomeAtual = produtoRepository.buscarNomeArquivo(metadados.getId());
//		Importante salvar metadados antes de salvar o conteúdo do arquivo (consistência do BD)
		FotoProduto metadadosT1 = produtoRepository.save(metadados);		
		produtoRepository.flush();
		if (nomeAtual != null) {
			fotoStorageService.removerArquivo(nomeAtual);
		}
		fotoStorageService.salvarArquivo(fotoProdutoInput, metadados.getNomeArquivo());		
		return metadadosT1;
	}
		
	public FotoProduto buscarMetadados (Long produtoId) {
		FotoProduto metadados = produtoRepository.buscarMetadados(produtoId);
		if (metadados != null) {
			return metadados;
		}
		throw new FotoNaoEncontradaException("A foto procurada não foi encontrada");
	}
	
	public FotoRetorno buscarFoto (FotoProduto metadados, String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		String nomeArquivo = metadados.getNomeArquivo();
		MediaType mediaType = MediaType.parseMediaType(metadados.getContentType());		
		List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
		verificarCompatibilidadeMediaType (mediaType, mediaTypesAceitas);
		FotoRetorno foto = fotoStorageService.recuperarArquivo(nomeArquivo);
		return foto;
	}
	
	
	private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypesAceitas) 
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}

	@Transactional
	public void removerFoto(FotoProduto metadados) {
		fotoStorageService.removerArquivo(metadados.getNomeArquivo());
		produtoRepository.removerMetadados(metadados);
	}

}
