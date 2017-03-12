package org.manuel.teambuilting.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
	private List<Results> results;

	@NotNull
	private String status;

	@PersistenceConstructor
	public Geocoding(final List<Results> results, final String status) {
		this.results = results;
		this.status = status;
	}

	@Immutable
	@Document
	@Data
	@lombok.Builder
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize
	public static class Results {
		private List<AddressComponent> address_components;
		private GeometryGoogle geometry;
		private String place_id;
		private String[] types;
	}

	@Immutable
	@Document
	@Data
	@lombok.Builder
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize
	public static class AddressComponent {
		private String long_name;
		private String short_name;
		private String[] types;
	}

	@Immutable
	@Document
	@Data
	@lombok.Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize
	public static class GeometryGoogle {
		private Location location;
		private String location_type;
	}

	@Immutable
	@Document
	@Data
	@lombok.Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@NoArgsConstructor
	@JsonIgnoreProperties
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonDeserialize
	public static class Location {
		private double lat;
		private double lng;
	}

}