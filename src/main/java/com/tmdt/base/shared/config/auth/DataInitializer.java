package com.tmdt.base.shared.config.auth;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.model.UserAccount;
import com.tmdt.base.domain.repository.RoleRepository;
import com.tmdt.base.domain.repository.auth.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDefaultAdmin(
            RoleRepository roleRepository,
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userAccountRepository.findByUsername("admin").isPresent()) {
                return;
            }

            Role adminRole = roleRepository.findByRoleName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setRoleName("ADMIN");
                        return roleRepository.save(r);
                    });

            UserAccount admin = new UserAccount();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setDisplayname("Administrator");
            admin.setEmail("admin@example.com");
            admin.setAddress("N/A");
            admin.setPhoneNumber("0000000000");
            admin.setAvatar(null);
            admin.setRoles(Set.of(adminRole));

            userAccountRepository.save(admin);

            log.info("Default admin user created with username='admin' and password='admin123'");
        };
    }
}

