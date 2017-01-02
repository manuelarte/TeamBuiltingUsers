package org.manuel.teambuilting.core.messages;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.model.Player;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Event Message to show that a player was deleted
 *
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
public class PlayerDeletedMessage {

    @NotNull
    private final Player player;

    @NotNull
    private final String userId;

    @NotNull
    private final Date date;
}
