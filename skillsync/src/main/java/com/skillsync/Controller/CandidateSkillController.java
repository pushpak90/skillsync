package com.skillsync.Controller;

import com.skillsync.DTO.CandidateSkillDTO;
import com.skillsync.Service.CandidateSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate-skills")
@RequiredArgsConstructor
public class CandidateSkillController {
    private final CandidateSkillService candidateSkillService;

    @PostMapping
    public ResponseEntity<CandidateSkillDTO> addSkill(@RequestBody CandidateSkillDTO dto){
        return ResponseEntity.ok(candidateSkillService.addSkillToCandidate(dto));
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<List<CandidateSkillDTO>> getSkills(@PathVariable Long candidateId){
        return ResponseEntity.ok(candidateSkillService.getCandidateSkills(candidateId));
    }
}
