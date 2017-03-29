package org.manuel.teambuilting.users.config;

import com.auth0.spring.security.api.Auth0SecurityConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.client.RestOperations;

/**
 * @author Manuel Doncel Martos
 * @since 29/03/2017.
 */
@Configuration
@EnableConfigurationProperties(Auth0Properties.class)
public class AppConfig extends Auth0SecurityConfig {

    @Override
    protected void authorizeRequests(final HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public RestOperations restOperations(RestTemplateBuilder builder) {
        return builder.build();
    }

}
