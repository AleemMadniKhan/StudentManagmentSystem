package com.StudentManagmentSystem.SMS.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.StudentManagmentSystem.SMS.filter.JwtFilter;
import com.StudentManagmentSystem.SMS.security.MyUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private MyUserDetailsService myUserDetailsService;
    private JwtFilter jwtFilter;
    SecurityConfig(MyUserDetailsService myUserDetailsService, JwtFilter jwtFilter){
        this.myUserDetailsService = myUserDetailsService;
        this.jwtFilter = jwtFilter;
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(custumizer -> custumizer.disable())
        .authorizeHttpRequests(request -> request
        .requestMatchers("/auth/**","/api/**","/error", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
        .anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:5173", "https://student-managment-system-frontend-eight.vercel.app"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean 
    AuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean 
    AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
}
