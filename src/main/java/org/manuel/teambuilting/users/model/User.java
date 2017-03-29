package org.manuel.teambuilting.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author manuel.doncel.martos
 * @since 29-3-2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@NotNull
	private String user_id;
	@NotNull
	private String email;
	@NotNull
	private String picture;
}
