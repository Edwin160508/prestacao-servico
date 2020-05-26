package io.github.edwinlima.rest.exceptionhandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.edwinlima.domain.exception.ClienteCpfCadastradoException;

@RestControllerAdvice
public class ApplicationControllerAdviceHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Método valida campos do Bean Validation
	 * @param bindingResult
	 * @return ResponseEntity
	 */
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ApiErrors> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Método para carregar lista de campos não aprovados no Bean Validation
	 * @param bindingResult
	 * @return List<>
	 */
	private List<ApiErrors> criarListaDeErros(BindingResult bindingResult) {
		List<ApiErrors> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new ApiErrors(mensagemUsuario, mensagemDesenvolvedor));
		}
			
		return erros;
	}
	/**
	 * Exception levantada quando não encontra registro em base de dados.
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<ApiErrors> erros = Arrays.asList(new ApiErrors(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}	
	
	@ExceptionHandler(ClienteCpfCadastradoException.class)
	public ResponseEntity<Object> handleClienteCpfCadastradoException(ClienteCpfCadastradoException ne, WebRequest request){
		String mensagemUsuario = messageSource.getMessage("cliente.com.mesmo.cpf", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ne.toString();
		List<ApiErrors> erros = Arrays.asList(new ApiErrors(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ne, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
}
