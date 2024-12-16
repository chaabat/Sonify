package com.sonify.serviceTest;

import com.sonify.controller.AuthController;
import com.sonify.dto.UserDTO;
import com.sonify.exception.ResourceAlreadyExistsException;
import com.sonify.exception.ResourceNotFoundException;
import com.sonify.model.Role;
import com.sonify.model.User;
import com.sonify.repository.UserRepository;
import com.sonify.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthentificationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerSuccess() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test@example.com");
        userDTO.setPassword("password123");

        Role userRole = new Role();
        userRole.setId("1");
        userRole.setName("USER");

        User savedUser = new User();
        savedUser.setId("1");
        savedUser.setLogin(userDTO.getLogin());
        savedUser.setPassword("encodedPassword");
        savedUser.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        savedUser.setRoles(roles);

        when(userRepository.existsByLogin(anyString())).thenReturn(false);
        when(mongoTemplate.findOne(any(Query.class), eq(Role.class), eq("roles"))).thenReturn(userRole);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt.token.here");

        // Act
        ResponseEntity<?> response = authController.register(userDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof UserDTO);
        UserDTO responseDTO = (UserDTO) response.getBody();
        assertEquals(userDTO.getLogin(), responseDTO.getLogin());
        assertNotNull(responseDTO.getToken());
        verify(userRepository).save(any(User.class));
    }

 

    @Test
    void loginSuccess() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test@example.com");
        userDTO.setPassword("password123");

        User user = new User();
        user.setId("1");
        user.setLogin(userDTO.getLogin());
        user.setPassword("encodedPassword");
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setName("USER");
        roles.add(userRole);
        user.setRoles(roles);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null); // Authentication success
        when(userRepository.findByLogin(userDTO.getLogin())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwt.token.here");

        // Act
        ResponseEntity<?> response = authController.login(userDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof UserDTO);
        UserDTO responseDTO = (UserDTO) response.getBody();
        assertEquals(userDTO.getLogin(), responseDTO.getLogin());
        assertNotNull(responseDTO.getToken());
        verify(jwtService).generateToken(user);
    }

    @Test
    void loginFailInvalidCredentials() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test@example.com");
        userDTO.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> {
            authController.login(userDTO);
        });
    }

 
}
