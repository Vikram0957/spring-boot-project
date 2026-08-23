package com.security.service;

import com.security.dto.AuthenticationResponse;
import com.security.dto.LoginRequest;
import com.security.dto.RegisterRequest;
import com.security.entity.Role;
import com.security.entity.UserDetailsImpl;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        Set<Role> roles = request.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = Set.of(Role.USER);
        }

        UserDetailsImpl user = UserDetailsImpl.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        
        userRepository.save(user);
        String jwtToken = jwtUtils.generateToken(user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
    
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        var jwtToken = jwtUtils.generateToken(user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}