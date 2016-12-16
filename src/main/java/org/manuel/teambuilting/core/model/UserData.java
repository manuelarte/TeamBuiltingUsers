package org.manuel.teambuilting.core.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.manuel.teambuilting.core.validations.PlayerExists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Manuel on 11/12/2016.
 */
@Component
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserData {

    @Id
    private String id;

    @NotNull
    @Indexed
    private String userId;

    @PlayerExists
    @Setter
    private String playerId;

    @NotNull
    private Set<String> adminOfTeams;

    @PersistenceConstructor
    public UserData(final String userId, final String playerId, final Set<String> adminOfTeams) {
        this.userId = userId;
        this.playerId = playerId;
        this.adminOfTeams = adminOfTeams;
    }

}
