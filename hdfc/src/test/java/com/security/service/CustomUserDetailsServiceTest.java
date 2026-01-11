package com.security.service;

import com.security.entity.User;
import com.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsername_success() {

        String username = "vikram";

        Optional<User> user = Optional.of(
                new User(
                        1L,
                        username,
                        "password",
                        "email@gmail.com",
                        new HashSet<>(),
                        false,
                        false,
                        false,
                        false
                )
        );

        when(userRepository.findByUsername(username))
                .thenReturn(user);

        UserDetails result =
                customUserDetailsService.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void loadUserByUsername_fail() {

        String username = "Dilip";

        Optional<User> user = Optional.of(
                new User(
                        1L,
                        "Vikram",
                        "password",
                        "email@gmail.com",
                        new HashSet<>(),
                        false,
                        false,
                        false,
                        false
                )
        );

        when(userRepository.findByUsername(username))
                .thenReturn(user);

        UserDetails result =
                customUserDetailsService.loadUserByUsername(username);

        assertNotNull(result);
        assertNotEquals(username, result.getUsername());
    }
}