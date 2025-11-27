package com.skillsync.DTO;

import lombok.Data;

import java.util.List;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private Long employerId;

    private List<JobSkillDTO> requiredSkills;
}
