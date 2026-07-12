package com.survey_backend_sky.service;

import com.survey_backend_sky.entity.Role;
import com.survey_backend_sky.entity.User;
import com.survey_backend_sky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }

        user.setEnabled(true);

        return userRepository.save(user);

    }

    public User updateUser(Long id, User request) {

        User user = getUser(id);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        return userRepository.save(user);

    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

    public User changeRole(Long id, Role role) {

        User user = getUser(id);

        user.setRole(role);

        return userRepository.save(user);

    }

    public User changeStatus(Long id, Boolean enabled) {

        User user = getUser(id);

        user.setEnabled(enabled);

        return userRepository.save(user);

    }

}