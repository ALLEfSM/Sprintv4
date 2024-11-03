package br.com.fiap.insights.configurações;

import br.com.fiap.insights.model.Role;
import br.com.fiap.insights.model.Usuario;
import br.com.fiap.insights.repository.RoleRepository;
import br.com.fiap.insights.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                adminRole.setLabel("Admin");
                roleRepository.save(adminRole);
            }

            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                userRole.setLabel("User");
                roleRepository.save(userRole);
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user1").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user1");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").get()));
                userRepository.save(user);
            }
        };
    }
}