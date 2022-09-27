package spotify.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(value = {UserNotFoundException.class, RoleNotFoundException.class, CityNotFoundEXception.class, NullException.class})
	public ResponseEntity<Error> dealWithNotFound(Exception exception, HttpServletRequest request) {

		return new ResponseEntity<>(Error.builder()
				.timestamp(new Date())
				.message(exception.getMessage())
				.method(request.getMethod())
				.path(request.getRequestURI())
				.build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {UserAlreadyExistsException.class, RoleAlreadyExistsException.class,
			UserAlreadyHasThatRole.class, CityAlreadyExistsException.class, ReviewAlreadyExistsException.class})
	public ResponseEntity<Error> dealWithAlreadyExists(Exception exception, HttpServletRequest request) {

		return new ResponseEntity<>(Error.builder()
				.timestamp(new Date())
				.message(exception.getMessage())
				.method(request.getMethod())
				.path(request.getRequestURI())
				.build(), HttpStatus.CONFLICT);
	}

	public void logError(Exception exception, HttpServletRequest request) {
		String logErrorMessage = request.getMethod()
				.concat(" ")
				.concat(request.getRequestURI())
				.concat(": ")
				.concat(exception.getMessage());

		LOGGER.error(logErrorMessage);
	}
}
