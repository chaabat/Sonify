package com.sonify.service.implementation;

import com.sonify.dto.UserDTO;
import com.sonify.mapper.UserMapper;
import com.sonify.model.Role;
import com.sonify.model.User;
import com.sonify.repository.UserRepository;
import com.sonify.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO updateUserRoles(String id, Set<String> roleNames) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Set<Role> roles = roleNames.stream()
                .map(name -> {
                    Role role = new Role();
                    role.setName(name);
                    return role;
                })
                .collect(Collectors.toSet());
        
        user.setRoles(roles);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
