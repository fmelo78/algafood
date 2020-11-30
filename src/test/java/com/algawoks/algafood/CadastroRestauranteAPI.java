package com.algawoks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.repository.CozinhaRepository;
import com.algawoks.algafood.domain.repository.RestauranteRepository;
import com.algawoks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import json.ResourceUtils;

// Define que o WebServer (Tomcat) deverá ser iniciado para servir a aplicação
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroRestauranteAPI {
	
	private static final int RESTAURANTEID_INVALIDO = 100;

//	Captura a porta HTTP utilizada pelo WebServer
	@LocalServerPort
	private int port;
	private int numeroRestaurantes;
	private Restaurante restauranteExistente;
	private String novoRestaurante = ResourceUtils.getContentFromResource("/json/sucesso/novo-restaurante.json");
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@BeforeEach
	void setUp() {
//		Em caso de falha, exibe o log de erros na console, para ajudar no debug
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test 
	void shouldRetornar200_WhenConsultarRestaurante() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test 
	void shouldRetornarCozinhas_WhenListarrestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(numeroRestaurantes));
	}
	
	@Test 
	void shouldRetornar201_WhenCadastrarCozinhas() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(novoRestaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test 
	void shouldRetornarCorretamente_WhenBuscarRestauranteExistente() {
		given()
			.pathParam("restauranteId", restauranteExistente.getId())
			.contentType(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(restauranteExistente.getNome()));
	}
	
	@Test 
	void shouldRetornarErro_WhenBuscarCozinhaInexistente() {
		given()
			.pathParam("restauranteId", RESTAURANTEID_INVALIDO)
			.contentType(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
//	---------------------------------------------------------------------	
	
	
	private void prepararDados() {
		Cozinha c1 = new Cozinha();
		c1.setNome("Tailandesa");
		cozinhaRepository.save(c1);
		Cozinha c2 = new Cozinha();
		c2.setNome("Americana");
		cozinhaRepository.save(c2);
		
		Restaurante r1 = new Restaurante();
		r1.setNome("Thai Food1");
		r1.setCozinha(c1);
		r1.setTaxaFrete(new BigDecimal(10.1));
		restauranteRepository.save(r1);
		Restaurante r2 = new Restaurante();
		r2.setNome("Thai Food2");
		r2.setCozinha(c2);
		r2.setTaxaFrete(new BigDecimal(9.4));
		restauranteRepository.save(r2);
		
		restauranteExistente = r2;
		numeroRestaurantes = (int) restauranteRepository.count();
	}
	
}
