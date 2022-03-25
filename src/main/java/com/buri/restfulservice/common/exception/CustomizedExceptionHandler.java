package com.buri.restfulservice.common.exception;

import com.buri.restfulservice.domain.user.exception.UserNotFoundException;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
		Exception ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse =
			new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							 .body(exceptionResponse);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(
		Exception ex,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse =
			new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(exceptionResponse);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),
			"Validation Failed",
			ex.getBindingResult()
			  .toString()
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(exceptionResponse);
	}
}
