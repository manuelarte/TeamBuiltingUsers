package org.manuel.teambuilting.users.config;

import com.auth0.client.auth.AuthAPI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

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

}
