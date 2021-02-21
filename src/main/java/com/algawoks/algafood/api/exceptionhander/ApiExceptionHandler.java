package com.algawoks.algafood.api.exceptionhander;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algawoks.algafood.api.exceptionhander.Problem.Field;
import com.algawoks.algafood.core.security.AlgaSecurity;
import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.infrastructure.exception.ReportException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AlgaSecurity algasecurity;
	
	private static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro de sistema interno e inesperado. "
			+ "Tente novamente e se o erro persistir, entre em contato com o administrador do sistema.";
	

	@ExceptionHandler (EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		Problem problema = gerarProblema(status, problemType, ex.getMessage(), ex.getMessage(), null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler (EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		Problem problema = gerarProblema(status, problemType, ex.getMessage(), ex.getMessage(), null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler (NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.SOLICITACAO_INCORRETA;
		Problem problema = gerarProblema(status, problemType, ex.getMessage(), ex.getMessage(), null);		
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<?> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException ex, WebRequest request){

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String param = ex.getParameter().getParameter().getName();
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Informe um valor compatível "
				+ "com o tipo %s.", param, ex.getValue(), ex.getRequiredType().getSimpleName());
		String userMessage = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problema = gerarProblema(status, problemType, detail, userMessage, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler (Exception.class)
	private ResponseEntity<?> handleUncaughtException (Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO_GENERICO_USUARIO_FINAL;
		ex.printStackTrace();
		Problem problema = gerarProblema(status, problemType, detail, detail, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler (ReportException.class)
	public ResponseEntity<?> handleReportException(ReportException ex, WebRequest request){
		log.error(ex.getMessage(), ex);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problema = gerarProblema(status, problemType, ex.getMessage(), detail, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@ExceptionHandler (AccessDeniedException.class)
	public ResponseEntity<?> handleReportException(AccessDeniedException ex, WebRequest request){
		log.error(ex.getMessage(), ex);
		HttpStatus status = HttpStatus.FORBIDDEN;
		ProblemType problemType = ProblemType.ACESSO_NEGADO;
		String detail = String.format("O usuário %s não possui permissão para executar essa operação", algasecurity.getUserName());
		Problem problema = gerarProblema(status, problemType, ex.getMessage(), detail, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	
	
//	Especialização da exceção HttpMessageNotReadable, já tratada pela ResponseEntityExceptionHandler
//	INICIO DO BLOCO
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException ((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemType problemType = ProblemType.SOLICITACAO_INCORRETA;
		String detail = "Corpo da mensagem em formato incorreto. Por favor, verifique a sintaxe";
		String userMessage = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problema = gerarProblema(status, problemType, detail, userMessage, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status).headers(headers).build();
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException rootCause, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
//		String path = rootCause.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		String path = gerarPath(rootCause);
		ProblemType problemType = ProblemType.SOLICITACAO_INCORRETA;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Informe um valor compatível "
				+ "com o tipo %s.", path, rootCause.getValue(), rootCause.getTargetType().getSimpleName());
		String userMessage = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problema = gerarProblema(status, problemType, detail, userMessage, null);
		return handleExceptionInternal(rootCause, problema, null, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException rootCause,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
//		String path = rootCause.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		String path = gerarPath(rootCause);
		ProblemType problemType = ProblemType.SOLICITACAO_INCORRETA;
		String detail = String.format("A propriedade '%s' é ignorada pela API. Por favor, a remova da requisição", path);
		String userMessage = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problema = gerarProblema(status, problemType, detail, userMessage, null);
		return handleExceptionInternal(rootCause, problema, null, status, request);
	}
// 	FIM DO BLOCO
	
	
	
//	Especialização da exceção HttpMessageNotReadable, já tratada pela ResponseEntityExceptionHandler
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
	
		String param = ex.getRequestURL();
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso '%s' que você tentou acessar é inexistente", param);
		Problem problema = gerarProblema(status, problemType, detail, detail, null);
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	
	
//	Especialização da exceção handleMethodArgumentNotValid, já tratada pela ResponseEntityExceptionHandler
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
		Problem problema = listarCamposErros(objectErrors, status);
		
		return handleExceptionInternal(ex, problema, null, status, request);
	}

//	Especialização da exceção handleBindException, já tratada pela ResponseEntityExceptionHandler
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		
		List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
		Problem problema = listarCamposErros(objectErrors, status);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	
	
//	Métodos para construção do problema e resposta a exceções genéricas
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problem.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.type(null)
					.title(status.getReasonPhrase())
					.detail(null)
					.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
					.build();
		}
		else if (body instanceof String){
			body = Problem.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.type(null)
					.title(status.getReasonPhrase())
					.detail(ex.getMessage())
					.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
//	private Problem gerarProblema (HttpStatus status, ProblemType problemType, String detail, String userMessage) {
//		Problem problema = Problem.builder()
//								.timestamp(OffsetDateTime.now())
//								.status(status.value())
//								.type(problemType.getUri())
//								.title(problemType.getTitle())
//								.detail(detail)
//								.userMessage(userMessage)
//								.build();
//		return problema;
//	}
	
	private Problem gerarProblema (HttpStatus status, ProblemType problemType, String detail, String userMessage, List<Field> fields) {
		Problem problema = Problem.builder()
								.timestamp(OffsetDateTime.now())
								.status(status.value())
								.type(problemType.getUri())
								.title(problemType.getTitle())
								.detail(detail)
								.userMessage(userMessage)
								.objects(fields)
								.build();
		return problema;
	}
	
	private String gerarPath (JsonMappingException ex) {
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		return path;
	}
	
	
	private Problem listarCamposErros(List<ObjectError> objectErrors, HttpStatus status) {
		
		List<Field> objects = new ArrayList<>();
		
		for (ObjectError valor : objectErrors) {
//			Qualquer uma das 2 formas abaixo é válida
//			fields.add(Field.builder().name(valor.getField()).userMessage(valor.getDefaultMessage()).build());
			if (valor instanceof FieldError) {
				String customMessage = messageSource.getMessage(valor, LocaleContextHolder.getLocale());
				objects.add(new Field(((FieldError) valor).getField(), customMessage));
			}
			else {
				String customMessage = messageSource.getMessage(valor, LocaleContextHolder.getLocale());
				objects.add(new Field(valor.getObjectName(), customMessage));
			}
		}
		
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		Problem problema = gerarProblema(status, problemType, detail, null, objects);
		return problema;
	}
	
}
