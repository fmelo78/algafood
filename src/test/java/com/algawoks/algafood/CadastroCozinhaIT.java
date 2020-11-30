package com.algawoks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algawoks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.service.CozinhaService;
import com.algawoks.algafood.domain.service.RestauranteService;

@SpringBootTest
class CadastroCozinhaIT {
	
	@Autowired
	CozinhaService cozinhaService;
	
	@Autowired
	RestauranteService restauranteService;

	@Test
	void shouldAtribuirId_WhenCadastrarCozinhaComDadosCorretos() {
		Cozinha c1 = new Cozinha();
		c1.setNome("Australiana");
		
		Cozinha c2 = cozinhaService.salvar(c1);
		
		assertThat(c2).isNotNull();
		assertThat(c2.getId()).isNotNull();
	}
	
	@Test 
	void shouldFalhar_WhenCadastrarCozinhaSemNome() {
		Cozinha c1 = new Cozinha();
		c1.setNome(null);
		
		assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(c1));
	}
	
	@Test 
	void shouldFalhar_WhenApagarCozinhaEmUso() {
		
		assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.remover(1L));
	}
	
	@Test 
	void shouldFalhar_WhenExcluirCozinhaInexistente() {
		
		assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.remover(1L));
	}
	
	
	
	
	
	

}
