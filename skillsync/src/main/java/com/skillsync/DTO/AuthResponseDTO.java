package com.skillsync.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String role;
}
