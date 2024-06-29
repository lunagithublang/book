package dev.arena.book_network.components;


import dev.arena.book_network.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dev.arena.book_network.entities.Role;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRoleInitializer implements CommandLineRunner {

//    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
//        final String userRoleName = "USER";
//        Optional<Role> existingRole = roleRepository.findByName(userRoleName);
//        if (existingRole.isEmpty()) {
//            Role role = Role.builder()
//                    .name(userRoleName)
//                    .build();
//            roleRepository.save(role);
//        }
    }
}
