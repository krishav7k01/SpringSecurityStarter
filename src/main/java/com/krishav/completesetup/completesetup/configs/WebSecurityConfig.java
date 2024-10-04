package com.krishav.completesetup.completesetup.configs;

import com.krishav.completesetup.completesetup.filters.JwtAuthFilter;
import com.krishav.completesetup.completesetup.handlers.Oauth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final Oauth2SuccessHandler oauth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        httpSecurity
                    .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/auth/**","/home.html").permitAll()
                   //            .requestMatchers("/employees/**").authenticated()
                                    .anyRequest().authenticated())
                    .csrf(carfConfig -> carfConfig.disable())
                    .sessionManagement(sessionConfig -> sessionConfig
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .oauth2Login(oauth2Config -> oauth2Config
                            .failureUrl("/login?error=true")
                            .successHandler(oauth2SuccessHandler));

     //               .formLogin(Customizer.withDefaults());

            return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService myInMemoryUserDetailService()
//    {
//        UserDetails userDetails = User
//                .withUsername("krishav")
//                .password(passwordEncoder().encode("krishav123"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("krishav1")
//                .password(passwordEncoder().encode("krishav123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails, adminUser);
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }




}
