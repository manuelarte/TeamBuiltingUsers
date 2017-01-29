package org.manuel.teambuilting.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@Data
@AllArgsConstructor
public class ValidationRuntimeException extends RuntimeException {

	private String errorCode;
	private String message;
	private String developerMessage;


}
