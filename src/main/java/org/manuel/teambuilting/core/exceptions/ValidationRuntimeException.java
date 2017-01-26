package org.manuel.teambuilting.core.exceptions;

import lombok.Data;

/**
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@Data
public class ValidationRuntimeException extends RuntimeException {

	private String errorCode;
	private String message;
	private String developerMessage;


}
