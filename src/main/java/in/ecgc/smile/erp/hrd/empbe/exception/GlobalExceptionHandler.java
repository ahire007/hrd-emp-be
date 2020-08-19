package in.ecgc.smile.erp.hrd.empbe.exception;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:errormap.properties")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleMailException(EmployeeNotFoundException ex, WebRequest request)
			throws Exception {
		String errorCode = env.getProperty(ExceptionConstantsMap.EMP_NOT_FOUND_EXCEPTION);
		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				ExceptionUtils.getStackTrace(ex)); // Sending Stack Trace is not compulsory ex.getMessage() is also
													// enough
		LOGGER.error("Exception:-{}",errorResponse);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public final ErrorResponse handleForbiddenException(HttpClientErrorException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getStatusCode().toString(), Calendar.getInstance().getTime(),
				"Client Error", ex.getStatusText(), ex.getMessage());
		LOGGER.error("Exception:-{}",error);
		return error;
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public final ErrorResponse handleAllExceptions(HttpServerErrorException ex, WebRequest request) {

		ErrorResponse error = new ErrorResponse(ex.getStatusCode().toString(), Calendar.getInstance().getTime(),
				"Server Error", ex.getStatusText(), ex.getMessage());
		LOGGER.error("Exception:-{}",error);

		return error;
	}

	@ExceptionHandler(SQLException.class)
	public final ErrorResponse handleSQLExceptions(SQLException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NO_CONTENT.toString(), Calendar.getInstance().getTime(),
				ex.getMessage(), "", ex.getMessage());
		LOGGER.error("Exception:-{}",error);
		
		return error;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> fieldErrorMap = new HashMap<String, String>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			fieldErrorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		String defaultMessage = "Validation Failed for " + ex.getParameter().getParameterType();
		String errorMessage;
		String errorCode = env
				.getProperty(ex.getParameter().getParameterType().getName() + ExceptionConstantsMap.VALIDATION_ERROR);

		if (errorCode != null) {
			errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
		} else {
			errorMessage = defaultMessage;
			errorCode = defaultMessage;
		}

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				fieldErrorMap.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
