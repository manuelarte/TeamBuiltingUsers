/**
 * 
 */
package org.manuel.teambuilting.dtos;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import com.mongodb.annotations.Immutable;

/**
 * @author Manuel Doncel Martos
 *
 */
@Immutable
public abstract class DTOId implements Serializable {
	
	private static final long serialVersionUID = -3395729369300243643L;
	
	@Id
	private final BigInteger id;
	
	public DTOId(final String id) {
		this(new BigInteger(id));
	}
	
	public DTOId(final BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}
	
	

}
