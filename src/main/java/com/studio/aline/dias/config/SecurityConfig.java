package com.studio.aline.dias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/home",
                    "/produtos", "/detalhesc", "/checkout1", "/checkout2", "/carrinho",
                    "/login", "/cadastro",
                    "/css/**", "/images/**", "/h2-console/**"
                ).permitAll()
                .requestMatchers("/api/login", "/api/cadastrar", "/api/usuarios/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")               // página customizada de login (GET)
                .loginProcessingUrl("/login")      // URL para processar o POST do login
                .defaultSuccessUrl("/#", true)  // redireciona para /home após login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout") // opcional: página após logout
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()) // cuidado com isso em produção
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // para funcionar h2-console

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
