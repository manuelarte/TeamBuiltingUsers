package org.manuel.teambuilting.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.annotations.Immutable;

import java.util.Map;

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
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
public class PlayerGeocoding extends AbstractGeocoding {

	@lombok.Builder
	@PersistenceConstructor
	public PlayerGeocoding(final String entityId, final double lat, final double lng, final Map<String, String> addressComponents) {
		this.entityId = entityId;
		this.lat = lat;
		this.lng = lng;
		this.addressComponents = addressComponents;
	}

}