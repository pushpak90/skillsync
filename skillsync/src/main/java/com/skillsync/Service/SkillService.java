package com.skillsync.Service;

import com.skillsync.DTO.SkillDTO;

import java.util.List;

public interface SkillService {
    SkillDTO createSkill(SkillDTO dto);
    List<SkillDTO> getSkills();
    SkillDTO getSkillById(Long id);
}
