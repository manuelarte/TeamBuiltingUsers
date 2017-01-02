package org.manuel.teambuilting.core.messages;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.model.Team;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
public class TeamEventMessage {

    @NotNull
    private final Team team;

    private final String userId;

    @NotNull
    private final String eventType;

    @NotNull
    private final Date date;
}
