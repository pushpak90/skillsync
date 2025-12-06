package com.skillsync.Controller;

import com.skillsync.DTO.MatchResultDTO;
import com.skillsync.Service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<MatchResultDTO>> matchCandidate(@PathVariable Long jobId){
        return ResponseEntity.ok(matchService.matchCandidateForJob(jobId));
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<MatchResultDTO>> matchJobs(@PathVariable Long candidateId){
        return ResponseEntity.ok(matchService.matchJobsForCandidate(candidateId));
    }
}
