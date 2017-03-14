package org.manuel.teambuilting.core.exceptions;

import lombok.Getter;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */

public enum ErrorCode {

	ID_NOT_FOUND("0001", "Entity %s with id %s not found"),
	PLAYER_DETAIL_FOR_SPORT_NOT_FOUND("0002", "The player with id %s does not have details for sport %s"),
	SPORT_NOT_FOUND("0010", "Sport %s not available");

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
