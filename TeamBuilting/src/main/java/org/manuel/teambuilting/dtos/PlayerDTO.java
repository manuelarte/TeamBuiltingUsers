/**
 * 
 */
package org.manuel.teambuilting.dtos;

import org.manuel.teambuilting.model.PlayerId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerDTO.Builder.class)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@lombok.Builder
public class PlayerDTO {
	
	private final PlayerId id;
	
	private final String name;
	
	private final String nickname;

	private final String bornAddress;

	public static class Builder {
		
	}

}