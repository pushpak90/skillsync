package com.skillsync.Service;

import com.skillsync.DTO.CandidateSkillDTO;

import java.util.List;

public interface CandidateSkillService {
    CandidateSkillDTO addSkillToCandidate(CandidateSkillDTO dto);

    List<CandidateSkillDTO> getCandidateSkills(Long candidateId);
}
