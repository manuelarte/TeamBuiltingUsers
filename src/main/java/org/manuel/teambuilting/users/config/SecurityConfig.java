package org.manuel.teambuilting.users.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Manuel Doncel Martos
 * @since 29/03/2017.
 */
@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(Auth0Properties.class)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Auth0Properties auth0Properties;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(auth0Properties.getClientId(), auth0Properties.getIssuer())
                //.forHS256WithBase64Secret(auth0Properties.getClientId(), auth0Properties.getIssuer(), auth0Properties.getClientSecret())
                .configure(http)
                .authorizeRequests()
                .anyRequest().permitAll();
    }

}
