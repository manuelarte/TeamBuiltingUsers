package org.manuel.teambuilting.core.validations;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.model.PlayerId;
import org.manuel.teambuilting.core.model.repository.PlayerRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Manuel on 12/12/2016.
 */
@Component
public class PlayerExistsValidator implements ConstraintValidator<PlayerExists, PlayerId> {

    private final PlayerRepository playerRepository;

    @Inject
    public PlayerExistsValidator(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void initialize(final PlayerExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(final PlayerId playerId, final ConstraintValidatorContext context) {
        final Player retrieved = playerRepository.findOne(playerId.getId());
        return retrieved != null;
    }
}
