package org.manuel.teambuilting.core.services.query;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */
public abstract class AbstractQueryService<Entity, ID extends Serializable, Repository extends CrudRepository<Entity, ID>> {

	protected final Repository repository;

	public AbstractQueryService(final Repository repository) {
		this.repository = repository;
	}

	/**
	 * Return an optional with the entity whose id matches the parameter id
	 *
	 * @param id the id of the entity desired
	 * @return an optional with the entity whose id matches the input parameter
	 */
	public Optional<Entity> findOne(final ID id) {
		final Optional<Entity> retrieved = Optional.ofNullable(repository.findOne(id));
		postFindOne(retrieved);
		return retrieved;
	}

	public abstract void postFindOne(final Optional<Entity> entity);

}
