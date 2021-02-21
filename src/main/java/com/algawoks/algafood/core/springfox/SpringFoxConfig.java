package com.algawoks.algafood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;
import com.algawoks.algafood.api.v1.model.output.PedidoResumoOutput;
import com.algawoks.algafood.api.v1.openapi.model.CozinhasOutputOpenApi;
import com.algawoks.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.algawoks.algafood.api.v1.openapi.model.PedidosOutputOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {
	
	TypeResolver typeResolver = new TypeResolver();

	@Bean
	public Docket apiDocketV1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algawoks.algafood.api.v1"))
//					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
//				desabilita as mensagens de erro padrão
				.useDefaultResponseMessages(false)
//				Especifica quais mensagens de erro serão exibidas para requisições GET
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class, File.class, InputStream.class, 
						Sort.class, URI.class, URL.class, URLStreamHandler.class, Page.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(ResponseEntity.class, CozinhaOutput.class),
						CozinhasOutputOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(ResponseEntity.class, PedidoResumoOutput.class),
						PedidosOutputOpenApi.class))
				.apiInfo(gerarApiInfoV1())
				.tags(new Tag("Cidade", "Gerencia as cidades"),
						new Tag ("Grupo", "Gerencia os grupos de usuários"),
						new Tag ("Cozinha", "Gerencias as cozinhas"),
						new Tag("Forma de Pagamento", "Gerencia as formas de pagamento"),
						new Tag("Pedido", "Gerencia os pedidos"),
						new Tag("Restaurante", "Gerencia os restaurantes"),
						new Tag("Estado", "Gerencia os estados"),
						new Tag("Usuario", "Gerencia os usuários"),
						new Tag("Estatística", "Fornece as estatísticas do sistema"),
						new Tag("Produto", "Gerencia os produtos"));
	}
	
	@Bean
	public Docket apiDocketV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V2")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algawoks.algafood.api.v2"))
//					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
//				desabilita as mensagens de erro padrão
				.useDefaultResponseMessages(false)
//				Especifica quais mensagens de erro serão exibidas para requisições GET
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class, File.class, InputStream.class, 
						Sort.class, URI.class, URL.class, URLStreamHandler.class, Page.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.apiInfo(gerarApiInfoV2())
				.tags(new Tag("Cidade", "Gerencia as cidades"),
						new Tag ("Cozinha", "Gerencias as cozinhas"));
	}
	
//	@Primary
//    @Bean
//    public LinkDiscoverers discoverers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//    }
	
//	  @Primary
//    @Bean
//    public PluginRegistry<LinkDiscoverer, MediaType> discoverers(
//            OrderAwarePluginRegistry<LinkDiscoverer, MediaType> relProviderPluginRegistry) {
//        return relProviderPluginRegistry;
//    }
	
	
	private List<ResponseMessage> globalPostResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Erro no formato da requisição")
	//				Nome referenciado na classe Problem
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação desejada pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Tipo de mídia não suportado pela API")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
				);
	}

	private List<ResponseMessage> globalPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Erro no formato da requisição")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação desejada pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Tipo de mídia não suportado pela API")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
				);
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Erro no formato da requisição")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
				);
	}


//	Deve conter apenas os códigos e mensagens de erro. Nunca os de sucesso (sem efeito)
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação desejada pelo consumidor")
					.build()
				);
	}
	
	
	private ApiInfo gerarApiInfoV1 () {
		return new ApiInfoBuilder()
				.title("Algafood API - DEPRECIADA")
				.description("API pública para clientes e restaurantes <br>"
						+ "<strong> API depreciada e será desativada em 01/01/2021. Favor utilizar a versão atualizada </strong>")
				.version("1.0")
				.contact(new Contact("Fernando Melo", "www.algaworks.com.br", "contato@algaworks.com.br"))
				.build();
	}
	
	private ApiInfo gerarApiInfoV2 () {
		return new ApiInfoBuilder()
				.title("Algafood API")
				.description("API pública para clientes e restaurantes - Versão 2")
				.version("2.0")
				.contact(new Contact("Fernando Melo", "www.algaworks.com.br", "contato@algaworks.com.br"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
