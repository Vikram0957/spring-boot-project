package com.example.jwtsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MODERATOR')")
    public ResponseEntity<String> userProfile(Authentication authentication) {
        return ResponseEntity.ok("Hello " + authentication.getName() + "! This is your profile.");
    }
    
    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminDashboard(Authentication authentication) {
        return ResponseEntity.ok("Hello Admin " + authentication.getName() + "! Welcome to admin dashboard.");
    }
    
    @GetMapping("/moderator/panel")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> moderatorPanel(Authentication authentication) {
        return ResponseEntity.ok("Hello " + authentication.getName() + "! Welcome to moderator panel.");
    }
    
    @GetMapping("/public/hello")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Hello! This is a public endpoint.");
    }
}