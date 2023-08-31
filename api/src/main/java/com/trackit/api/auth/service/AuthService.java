package com.trackit.api.auth.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trackit.api.auth.dto.AuthDto;
import com.trackit.api.auth.jwt.JwtService;
import com.trackit.api.user.model.Users;
import com.trackit.api.user.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;

    AuthService(UserRepository userRepository, JwtService jwtService) {
        this.repository = userRepository;
        this.jwtService = jwtService;
    }

    public String signin(AuthDto auth) throws UsernameNotFoundException {
        Users user = repository.findByEmail(auth.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (Boolean.TRUE.equals(decryptPassword(auth.password(), user.getPassword()))) {
            return jwtService.generateToken(auth);
        }
        throw new UsernameNotFoundException("Email/Password doesnt exists");
    }

    private Boolean decryptPassword(String password, String hashPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.matches(password, hashPassword);
    }
}
