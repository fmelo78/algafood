package com.algawoks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algawoks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.model.FormaPagamento;
import com.algawoks.algafood.domain.model.Produto;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Restaurante buscarOuFalhar (Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(() -> 
		new RestauranteNaoEncontradoException(restauranteId));
		return restaurante;
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		restaurante.setCozinha(cozinha);
		
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		restaurante.getEndereco().setCidade(cidade);
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void remover (Long restauranteId) {
		try {
			buscarOuFalhar(restauranteId);
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();
		}
		catch(DataIntegrityViolationException ex) {
//			throw new EntidadeEmUsoException(String.format("O restaurante de código %d possui produtos cadastrados", restauranteId));
			throw new EntidadeEmUsoException(Restaurante.class.getSimpleName(), restauranteId);
		}
	}

	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.setAtivo(true);
//		Como o método está anotado com @Transactional, não precisamos mandar salvar o objeto após 
//		a ativação do restaurante. O próprio contexto do JPA entende essa necessidade e faz o salvamento
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.setAtivo(false);
	}
	
	@Transactional
	public void desassociarFormaPagamento (Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.getFormasPagamento().remove(formaPagamento);
//		Não obrigatório, pois o objeto é gerenciado no contexto do JPA, mas prefiro deixar explícito
		salvar(restaurante);
	}
	
	@Transactional
	public void associarFormaPagamento (Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.getFormasPagamento().add(formaPagamento);
//		Não obrigatório, pois o objeto é gerenciado no contexto do JPA, mas prefiro deixar explícito
		salvar(restaurante);
	}
	
	public Produto buscarProdutoRestaurante(Long restauranteId, Long produtoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Produto produto = produtoService.buscarOuFalhar(produtoId);
		if (!restaurante.getProdutos().contains(produto)) {
			throw new ProdutoNaoEncontradoException(String.format("O restaurante de código %d não possui o produto de código %d", 
					restauranteId, produtoId));
		}
		return produto;
	}

	@Transactional
	public void abrir(Restaurante restaurante) {
		restaurante.setAberto(true);		
		salvar(restaurante);
	}

	@Transactional
	public void fechar(Restaurante restaurante) {
		restaurante.setAberto(false);
		salvar(restaurante);
	}

	@Transactional
	public void associarUsuario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		restaurante.getUsuariosResponsveis().add(usuario);
		salvar(restaurante);
	}

	@Transactional
	public void desassociarUsuario(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		restaurante.getUsuariosResponsveis().remove(usuario);
		salvar(restaurante);
	}

	@Transactional
	public void ativarVarios(List<Long> restaurantesId) {
		try {
			restaurantesId.forEach((restauranteId) -> ativar(restauranteId));		
		}
		catch (RestauranteNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}

	@Transactional
	public void desativarVarios(List<Long> restaurantesId) {
		try {
			for (Long valor : restaurantesId) {
				inativar(valor);
			}
	//		Abaixo, 2 formas alternativas de se implementar o mesmo código: 
	//		restaurantesId.forEach((restauranteId) -> inativar(restauranteId));
	//		restaurantesId.forEach(this::inativar);
		}
		catch (RestauranteNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	
	
}
