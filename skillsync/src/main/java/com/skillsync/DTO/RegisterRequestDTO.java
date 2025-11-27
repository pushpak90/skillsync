package com.skillsync.DTO;

import com.skillsync.Entity.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
