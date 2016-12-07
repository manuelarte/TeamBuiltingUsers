package org.manuel.teambuilting.core.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.core.model.Team;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
public class TeamChangedMessage {

    private final Team team;
    private final String changeType;
}
