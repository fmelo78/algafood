package com.algawoks.algafood.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.FormaPagamento;
import com.algawoks.algafood.domain.model.ItemPedido;
import com.algawoks.algafood.domain.model.Pedido;
import com.algawoks.algafood.domain.model.Produto;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.model.StatusPedido;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.repository.PedidoRepository;
import com.algawoks.algafood.domain.service.EmailService.Mensagem;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	public Pedido buscarOuFalhar (String codigo) {
		Pedido pedido = (pedidoRepository.findByCodigo(codigo)).orElseThrow(() -> new PedidoNaoEncontradoException(
				String.format("O pedido de código %s não existe", codigo)));
		return pedido;
	}
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarDadosBasicos(pedido);
		validarItens(pedido);	
		calcularTotal(pedido);
		Pedido pedidoT1 = pedidoRepository.save(pedido);
		return pedidoT1;
	}
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = buscarOuFalhar(codigoPedido);
		pedido.setDataConfirmacao(OffsetDateTime.now());
		alterarStatus(pedido, StatusPedido.CONFIRMADO);
		pedidoRepository.save(pedido);
		
//		Instanciamento da mensagem que será transmitida no email
		var mensagem = Mensagem.builder()
//				.assunto(pedido.getRestaurante().getNome() + " - Confirmação de pedido")
				.corpo(String.format("O pedido de código <strong> %s </strong> foi confirmado", pedido.getCodigo()))
				.destinatario(pedido.getCliente().getEmail())
				.build();
		emailService.enviar(mensagem);
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = buscarOuFalhar(codigoPedido);
		pedido.setDataEntrega(OffsetDateTime.now());
		alterarStatus(pedido, StatusPedido.ENTREGUE);
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = buscarOuFalhar(codigoPedido);
		pedido.setDataCancelamento(OffsetDateTime.now());
		alterarStatus(pedido, StatusPedido.CANCELADO);
		pedidoRepository.save(pedido);
	}
	
	

	
	
	
	
	
	
	
	
	private void alterarStatus (Pedido pedido, StatusPedido novoStatus) {
		StatusPedido statusAtual = pedido.getStatus();
		if (!statusAtual.permiteMudancaPara(novoStatus)) {
			throw new NegocioException(String.format("O pedido de código %s não pode ter seu status alterado de %s para %s",
					pedido.getCodigo(), statusAtual.getDescricao(), novoStatus.getDescricao()));
		}
		pedido.setStatus(novoStatus);
//		pedidoRepository.save(pedido);		
	}
	
	private void validarDadosBasicos (Pedido pedido ) {
		Long cidadeId = pedido.getEnderecoEntrega().getCidade().getId();
		Long usuarioId = pedido.getCliente().getId();
		Long restauranteId = pedido.getRestaurante().getId();
		Long formaPagamentoId = pedido.getFormaPagamento().getId();
		
		try {
			Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
			Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
			Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
			FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
			if (!restaurante.aceitaFormaPagamento(formaPagamento)) {
				throw new NegocioException(String.format("A forma de pagamento %s não é aceita pelo restaurante", formaPagamento));
			}
		
			pedido.getEnderecoEntrega().setCidade(cidade);
			pedido.setCliente(usuario);
			pedido.setRestaurante(restaurante);
			pedido.setFormaPagamento(formaPagamento);
			pedido.setTaxaFrete(restaurante.getTaxaFrete());
			pedido.setCodigo(UUID.randomUUID().toString());
			
		} catch (EntidadeNaoEncontradaException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	private void validarItens(Pedido pedido) {
		Long restauranteId = pedido.getRestaurante().getId();
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		List<ItemPedido> itensDoPedido = pedido.getItens();
		List<Produto> produtosDoRestaurante = restaurante.getProdutos();
		for (ItemPedido itemPedido : itensDoPedido) {
			Long produtoId = itemPedido.getProduto().getId();
			Produto produto = produtoService.buscarOuFalhar(produtoId);
			if (!produtosDoRestaurante.contains(produto)) {
				throw new NegocioException(String.format(
					"O produto de código %d não pertence ao restaurante de código %d", produtoId, restauranteId));
			}
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setPrecoUnitario(produto.getPreco());
			itemPedido.setPrecoTotal(itemPedido.getPrecoUnitario().multiply(new BigDecimal(itemPedido.getQuantidade())));
		}
	}
	
	private void calcularTotal (Pedido pedido) {
		BigDecimal subTotal = pedido.getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal total = subTotal.add(pedido.getTaxaFrete());
		pedido.setSubtotal(subTotal);
		pedido.setValorTotal(total);
	}


}
