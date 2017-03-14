package org.manuel.teambuilting.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.maps.model.GeocodingResult;
import com.mongodb.annotations.Immutable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
@Document
@Data
@lombok.Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
public class Geocoding {

	@NotNull
	private GeocodingResult[] results;

	@NotNull
	private String status;

	@PersistenceConstructor
	public Geocoding(final GeocodingResult[] results, final String status) {
		this.results = results;
		this.status = status;
	}

}