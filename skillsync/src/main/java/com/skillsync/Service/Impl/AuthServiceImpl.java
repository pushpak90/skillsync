package com.skillsync.Service.Impl;

import com.skillsync.DTO.AuthResponseDTO;
import com.skillsync.DTO.LoginRequestDTO;
import com.skillsync.DTO.RegisterRequestDTO;
import com.skillsync.Entity.User;
import com.skillsync.Repository.UserRepository;
import com.skillsync.Security.Jwt.JwtService;
import com.skillsync.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setRole(savedUser.getRole().name());

        return response;
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setRole(user.getRole().name());

        return response;
    }
}
