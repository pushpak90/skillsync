package com.skillsync.Service.Impl;

import com.skillsync.DTO.CandidateSkillDTO;
import com.skillsync.Entity.CandidateSkill;
import com.skillsync.Entity.Skill;
import com.skillsync.Entity.User;
import com.skillsync.Repository.CandidateSkillRepository;
import com.skillsync.Repository.SkillRepository;
import com.skillsync.Repository.UserRepository;
import com.skillsync.Service.CandidateSkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateSkillServiceImpl implements CandidateSkillService {
    private final CandidateSkillRepository candidateSkillRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    @Override
    public CandidateSkillDTO addSkillToCandidate(CandidateSkillDTO dto) {
        User candidate = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Candidate Not Found"));
        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill Not Found"));
        CandidateSkill cs = CandidateSkill.builder()
                .candidate(candidate)
                .skill(skill)
                .level(dto.getLevel())
                .build();

        CandidateSkill saved = candidateSkillRepository.save(cs);

        CandidateSkillDTO response = new CandidateSkillDTO();
        response.setUserId(saved.getCandidate().getId());
        response.setSkillId(saved.getSkill().getId());
        response.setLevel(saved.getLevel());

        return response;
    }

    @Override
    public List<CandidateSkillDTO> getCandidateSkills(Long candidateId) {
        List<CandidateSkill> list = candidateSkillRepository.findByCandidateId(candidateId);

        return list.stream()
                .map(cs -> {
                    CandidateSkillDTO dto = new CandidateSkillDTO();
                    dto.setUserId(cs.getCandidate().getId());
                    dto.setSkillId(cs.getSkill().getId());
                    dto.setLevel(cs.getLevel());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
