package org.manuel.teambuilting.users.config;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 * @since 28/05/2017.
 */
@Configuration
@AllArgsConstructor
public class AppConfig {

    private final Auth0Properties auth0Properties;

    @Bean
    public RestOperations restOperations(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public AuthAPI authAPI() {
        return new AuthAPI(auth0Properties.getDomain(), auth0Properties.getClientId(), auth0Properties.getClientSecret());
    }

    @Bean
    @SneakyThrows
    public TokenHolder tokenHolder(final AuthAPI authAPI) {
        final AuthRequest authRequest = authAPI.requestToken("https://manuelarte.eu.auth0.com/api/v2/");
        return authRequest.execute();
    }

    @Bean
    public ManagementAPI managementAPI(final TokenHolder tokenHolder) {
        return new ManagementAPI(auth0Properties.getDomain(), tokenHolder.getAccessToken());
    }

}
