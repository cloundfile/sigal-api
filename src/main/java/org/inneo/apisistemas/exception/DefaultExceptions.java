package org.inneo.apisistemas.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;

@Data
public class DefaultExceptions {
	private int code;
	private String message;
	private String exception;
	private List<FieldErrorItem> fieldErrors = new ArrayList<>();
	
	public static DefaultExceptions construir(int code, String message) {
		DefaultExceptions exception = new DefaultExceptions();
		exception.setCode(code);
		exception.setMessage(message);
		return exception;
	}
	
	public static DefaultExceptions construir(int code, String message, String ex) {
		DefaultExceptions exception = new DefaultExceptions();
		exception.setCode(code);
		exception.setMessage(message);
		exception.setException(ex);
		return exception;
	}
	
	public static DefaultExceptions construir(int code, String message, ConstraintViolationException e) {
		DefaultExceptions exception = new DefaultExceptions();
		exception.setCode(code);
		exception.setMessage(message);
		exception.setException(e.getMessage());
		
		for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
			exception.getFieldErrors().add(FieldErrorItem.construir(
					((PathImpl)constraint.getPropertyPath()).getLeafNode().getName(),
					constraint.getMessage()));
		}
		
		return exception;
	}
	
	public static DefaultExceptions construir(int code, String message, DataIntegrityViolationException ex) {				
		DefaultExceptions exception = new DefaultExceptions();
		exception.setCode(code);
		exception.setMessage(message);
		exception.setException(ex.getMessage());		
		
		return exception;
	}

	public static DefaultExceptions construir(int code, String message, ObjectFieldErrorsException ex) {

		DefaultExceptions exception = new DefaultExceptions();
		exception.setCode(code);
		exception.setMessage(message);
		exception.setException(ex.getMessage());
		exception.setFieldErrors(ex.getFieldErrors());

		return exception;
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(Exception e, WebRequest request) {
	    return new ResponseEntity<Object>("Acesso negado!", HttpStatus.UNAUTHORIZED);
	}
}
