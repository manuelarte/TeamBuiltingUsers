package org.manuel.teambuilting.core.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.core.model.Team;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
public class TeamEventMessage {

    @NotNull
    private final Team team;

    private final String user_id;

    @NotNull
    private final String eventType;

    @NotNull
    private final Date date;
}
