package com.segurity.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class WebSecurityConfig {


    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User
            .withUsername("wladimir")
            .password("123456")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .authorizeRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/alou-mundo/**").permitAll()
                    .requestMatchers("/categorias").authenticated()
                    .anyRequest().permitAll()
            }



        return http.build()
    }
}

