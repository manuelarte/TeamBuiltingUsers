package org.manuel.teambuilting.core.validations;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.manuel.teambuilting.core.model.Team;
import org.manuel.teambuilting.core.repositories.TeamRepository;
import org.springframework.stereotype.Component;

/**
 * @author Manuel on 12/12/2016.
 */
@Component
public class TeamExistsValidator implements ConstraintValidator<TeamExists, String> {

    private final TeamRepository teamRepository;

    @Inject
    public TeamExistsValidator(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void initialize(final TeamExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String teamId, final ConstraintValidatorContext context) {
        final Optional<Team> retrieved = Optional.ofNullable(teamRepository.findOne(teamId));
        return retrieved.isPresent();
    }
}
