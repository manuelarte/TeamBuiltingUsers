/**
 * 
 */
package org.manuel.teambuilting.core.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public abstract class ModelId implements Serializable {
	
	private static final long serialVersionUID = -3395729369300243643L;
	
	@Id
	private final ObjectId id;
	
	public ModelId(final String id) {
		this.id = new ObjectId(id);
	}

	@JsonValue
	public String getId() {
		return id.toHexString();
	}
	
	

}
