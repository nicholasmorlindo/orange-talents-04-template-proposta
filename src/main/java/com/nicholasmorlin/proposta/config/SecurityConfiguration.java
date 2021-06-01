package com.nicholasmorlin.proposta.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAnyAuthority("SCOPE_orange-talents-scope:write")
                .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAnyAuthority("SCOPE_orange-talents-scope:read")
                .antMatchers(HttpMethod.POST,"/api/bloqueios/**").hasAnyAuthority("SCOPE_orange-talents-scope:write")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
