package com.sonify.controller;

import com.sonify.dto.UserDTO;
import com.sonify.model.Role;
import com.sonify.model.User;
import com.sonify.repository.UserRepository;
import com.sonify.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setActive(true);

        // Set default role as USER
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setName("USER");
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(UserDTO.builder()
                .login(user.getLogin())
                .token(token)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword())
        );

        User user = userRepository.findByLogin(userDTO.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(UserDTO.builder()
                .login(user.getLogin())
                .token(token)
                .build());
    }
}
