package com.survey_backend_sky.service;

import com.survey_backend_sky.dto.AuthResponse;
import com.survey_backend_sky.dto.LoginRequest;
import com.survey_backend_sky.dto.RegisterRequest;
import com.survey_backend_sky.entity.Role;
import com.survey_backend_sky.entity.User;
import com.survey_backend_sky.repository.UserRepository;
import com.survey_backend_sky.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists");

        }

        User user = User.builder()

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .password(passwordEncoder.encode(request.getPassword()))

                .role(Role.ROLE_USER)

                .enabled(true)

                .build();

        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()

                .token(token)

                .role(user.getRole().name())

                .email(user.getEmail())

                .firstName(user.getFirstName())

                .lastName(user.getLastName())

                .build();

    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(() ->

                        new RuntimeException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()

                .token(token)

                .role(user.getRole().name())

                .email(user.getEmail())

                .firstName(user.getFirstName())

                .lastName(user.getLastName())

                .build();

    }

}