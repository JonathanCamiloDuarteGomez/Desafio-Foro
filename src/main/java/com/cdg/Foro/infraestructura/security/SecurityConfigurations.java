package com.cdg.Foro.infraestructura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;

    //metodo capaz de quitar las configuraciones por defecto de spring security
    @Bean//clase que tiene que ser cargada y esta disponible para que security lo utilice
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).
                sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(req->{
                    req.requestMatchers(
                                    HttpMethod.POST,
                                    "/login")
                            .permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated();//el resto de las peticiones necesitan autenticacion
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                //primero nuestro filtro
                //uris que no necesitan autenticacion
                .build();
    }

    //metodo capz de devolver un authenticationManager del token
    @Bean//para que spring lo utilice
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();//obtiene el authenticationManager
    }

    //metodo para que sepa el tipo de hasching que vamos a usar
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
