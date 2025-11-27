package com.skillsync.DTO;

import com.skillsync.Entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
