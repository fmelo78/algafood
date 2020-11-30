package com.algawoks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

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
import com.algawoks.algafood.domain.repository.CozinhaRepository;
import com.algawoks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import json.ResourceUtils;

// Define que o WebServer (Tomcat) deverá ser iniciado para servir a aplicação
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaAPI {
	
	private static final int COZINHAID_INVALIDO = 100;

//	Captura a porta HTTP utilizada pelo WebServer
	@LocalServerPort
	private int port;
	private int numeroCozinhas;
	private Cozinha cozinhaExistente;
	private String novaCozinha = ResourceUtils.getContentFromResource("/json/sucesso/nova-cozinha.json");
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@BeforeEach
	void setUp() {
//		Em caso de falha, exibe o log de erros na console, para ajudar no debug
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test 
	void shouldRetornar200_WhenConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test 
	void shouldRetornarCozinhas_WhenListarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(numeroCozinhas));
	}
	
	@Test 
	void shouldRetornar201_WhenCadastrarCozinhas() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(novaCozinha)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test 
	void shouldRetornarCorretamente_WhenBuscarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaExistente.getId())
			.contentType(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaExistente.getNome()));
	}
	
	@Test 
	void shouldRetornarErro_WhenBuscarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", COZINHAID_INVALIDO)
			.contentType(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
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
		
		cozinhaExistente = c2;
		numeroCozinhas = (int) cozinhaRepository.count();
	}
	
}
