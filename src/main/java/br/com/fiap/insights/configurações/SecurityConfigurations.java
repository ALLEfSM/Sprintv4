package br.com.fiap.insights.configurações;

import br.com.fiap.insights.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private  UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/login", "/css/**").permitAll()
                        .requestMatchers("/admin/**", "/register").hasRole("ADMIN")
                        .requestMatchers("/cliente/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/cliente/listar").hasRole("ADMIN")
                        .requestMatchers("/cliente/excluir").hasRole("ADMIN")
                        .requestMatchers("/produto/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/produto/listar").hasRole("ADMIN")
                        .requestMatchers("/produto/excluir").hasRole("ADMIN")
                        .requestMatchers("/opiniao/excluir").hasRole("ADMIN")
                        .requestMatchers("/usuario/registrar").hasRole("ADMIN")

                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
