package com.skillsync.Service;

import com.skillsync.DTO.AuthResponseDTO;
import com.skillsync.DTO.LoginRequestDTO;
import com.skillsync.DTO.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}
