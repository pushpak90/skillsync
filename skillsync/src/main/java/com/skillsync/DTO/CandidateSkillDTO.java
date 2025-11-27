package com.skillsync.DTO;

import lombok.Data;

@Data
public class CandidateSkillDTO {
    private Long userId;
    private Long skillId;
    private int level;
}
