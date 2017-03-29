package org.manuel.teambuilting.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Data
@Builder
public class User {

	@NotNull
	private final String user_id;
	@NotNull
	private final String email;
	@NotNull
	private final String imageLink;
}
