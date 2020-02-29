package com.example.igreja.errorHandle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.igreja.errorHandle.Problema.Campo;
import com.example.igreja.exception.EntidadeNaoEncontrada;
import com.example.igreja.exception.ViolacaoException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Campo> mensagens = new ArrayList<>();
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			Campo campo = Campo.builder().field(fieldError.getField()).mensagem(fieldError.getField() + " Não pode ser nullo").build();
			mensagens.add(campo);
		}
		
		Problema problema = Problema.builder()
				.hotaOcorrida(OffsetDateTime.now())
				.status(status.value())
				.tipo("https://igreja.com.br/error-de-fields")
				.titulo("fiels estão incorretos")
				.detalhes("Algum campo está incorreto, por favor verifique")
				.campos(mensagens)
				.build();
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problema problema = Problema.builder()
				.status(status.value())
				.tipo("https://igreja.com.br/mensagem-nao-identifacada")
				.titulo(status.getReasonPhrase())
				.detalhes(ex.getLocalizedMessage())
				.build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(ViolacaoException.class)
	public ResponseEntity<?> handlerExecptionVioloation(ViolacaoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = Problema.builder()
				.status(status.value())
				.tipo("https://igreja.com.br/error-de-field")
				.titulo(status.getReasonPhrase())
				.detalhes("Error ao passar algum parametro, por favor verifique")
				.hotaOcorrida(OffsetDateTime.now())
				.build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ResponseEntity<?> handleExceptionEntidadeNaoEncontrada(EntidadeNaoEncontrada ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problema problema = Problema.builder().status(status.value()).tipo("https://igreja.com.br/entidade-nao-encontrada")
				.titulo(status.getReasonPhrase()).detalhes(ex.getMessage()).hotaOcorrida(OffsetDateTime.now()).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problema.builder()
					.titulo(status.getReasonPhrase())
					.status(status.value())
					.build();
		} else if (body instanceof String) {
			body = Problema.builder()
					.titulo((String) body)
					.status(status.value())
					.build();
		}
	return super.handleExceptionInternal(ex, body, headers, status, request);
	}
		
}
	
