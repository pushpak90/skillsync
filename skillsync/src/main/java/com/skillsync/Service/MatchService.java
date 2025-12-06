package com.skillsync.Service;

import com.skillsync.DTO.MatchResultDTO;

import java.util.List;

public interface MatchService {
    List<MatchResultDTO> matchCandidateForJob(Long jobId);
    List<MatchResultDTO> matchJobsForCandidate(Long candidate);
}
