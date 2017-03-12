package org.manuel.teambuilting.core.exceptions;

import lombok.Getter;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */

public enum ErrorCode {

	ID_NOT_FOUND("0001", "Entity %s with id %s not found");

	@Getter
	final String errorCode;
	final String message;

	ErrorCode(final String errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage(final Object... args) {
		return String.format(message, args);
	}
}
