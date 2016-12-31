package org.manuel.teambuilting.core.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.core.model.Player;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private final String user_id;

    @NotNull
    private final Date date;
}
