package org.manuel.teambuilting.core.model;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
@Data
public class AbstractGeocoding {

	@Id
	protected ObjectId id;

	@NotNull
	protected String entityId;

	@NotNull
	protected double lat;

	@NotNull
	protected double lng;

	@NotNull
	protected Map<String, String> addressComponents;

}
