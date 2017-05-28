package org.manuel.teambuilting.users.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Manuel Doncel Martos
 * @since 29/03/2017.
 */
@Component
@ConfigurationProperties(prefix = "auth0", locations={"classpath:auth0.yaml"})
@Validated
@Data
public class Auth0Properties {

    @NotNull
    private String domain;

    @NotNull
    private String issuer;

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;

}
