package org.studentsuccessplan.ssp.web.api;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.studentsuccessplan.ssp.service.ObjectNotFoundException;
import org.studentsuccessplan.ssp.transferobject.ServiceResponse;

public abstract class BaseController {
	/**
	 * Log and return an appropriate message for a page not found (HTTP 404,
	 * {@link HttpStatus#NOT_FOUND}).
	 * 
	 * @param e
	 *            Original exception
	 * @return An appropriate service response message to send to the client.
	 */
	@PreAuthorize("permitAll")
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody
	ServiceResponse handleNotFound(final ObjectNotFoundException e) {
		getLogger().error("Error: ", e);
		return new ServiceResponse(false, e.getMessage());
	}

	/**
	 * Log and return an appropriate message for a bad request error (
	 * {@link HttpStatus#BAD_REQUEST}).
	 * 
	 * @param e
	 *            Original exception
	 * @return An appropriate service response message to send to the client.
	 */
	@PreAuthorize("permitAll")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody
	ServiceResponse handleValidationError(
			final MethodArgumentNotValidException e) {
		getLogger().error("Error: ", e);
		return new ServiceResponse(false, e);
	}

	/**
	 * Log and return an appropriate message for an access denied error (
	 * {@link HttpStatus#FORBIDDEN}).
	 * 
	 * @param e
	 *            Original exception
	 * @return An appropriate service response message to send to the client.
	 */
	@PreAuthorize("permitAll")
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public @ResponseBody
	ServiceResponse handleAccessDenied(final AccessDeniedException e) {
		getLogger().error("Error: ", e);
		return new ServiceResponse(false, e.getMessage());
	}

	/**
	 * Log and return an appropriate message for an internal server error (HTTP
	 * 500, {@link HttpStatus#INTERNAL_SERVER_ERROR}).
	 * 
	 * @param e
	 *            Original exception
	 * @return An appropriate service response message to send to the client.
	 */
	@PreAuthorize("permitAll")
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	ServiceResponse handle(final Exception e) {
		getLogger().error("Error: ", e);
		return new ServiceResponse(false, e.getMessage());
	}

	protected abstract Logger getLogger();
}