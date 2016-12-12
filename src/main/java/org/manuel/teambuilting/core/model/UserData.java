package org.manuel.teambuilting.core.model;

import lombok.*;
import org.manuel.teambuilting.core.validations.PlayerExists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Set;

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

    private Set<String> adminOfTeams;

    @PersistenceConstructor
    public UserData(final String userId, final String playerId, final Set<String> adminOfTeams) {
        this.userId = userId;
        this.playerId = playerId;
        this.adminOfTeams = adminOfTeams;
    }

}
