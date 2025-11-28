package com.skillsync.Service.Impl;

import com.skillsync.DTO.SkillDTO;
import com.skillsync.Entity.Skill;
import com.skillsync.Repository.SkillRepository;
import com.skillsync.Service.SkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;
    @Override
    public SkillDTO createSkill(SkillDTO dto) {

        Skill skill = modelMapper.map(dto, Skill.class);
        Skill saved = skillRepository.save(skill);

        return modelMapper.map(saved, SkillDTO.class);
    }

    @Override
    public List<SkillDTO> getSkills() {
        return skillRepository.findAll()
                .stream().map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        Skill skill  = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill Not Found"));
        return modelMapper.map(skill, SkillDTO.class);
    }
}
