/**
 * 
 */
package org.manuel.teambuilting.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public abstract class ModelId implements Serializable {
	
	private static final long serialVersionUID = -3395729369300243643L;
	
	@Id
	private final String id;
	
	public ModelId(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	

}
