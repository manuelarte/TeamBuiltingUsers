package org.manuel.teambuilting.core.validations;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.manuel.teambuilting.core.model.Player;
import org.manuel.teambuilting.core.repositories.PlayerRepository;
import org.springframework.stereotype.Component;

/**
 * @author Manuel on 12/12/2016.
 */
@Component
public class PlayerExistsValidator implements ConstraintValidator<PlayerExists, String> {

    private final PlayerRepository playerRepository;

    @Inject
    public PlayerExistsValidator(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void initialize(final PlayerExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String playerId, final ConstraintValidatorContext context) {
        final Optional<Player> retrieved = Optional.ofNullable(playerRepository.findOne(playerId));
        return retrieved.isPresent();
    }
}
