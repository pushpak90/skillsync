package com.skillsync.Service.Impl;

import com.skillsync.DTO.MatchResultDTO;
import com.skillsync.Entity.*;
import com.skillsync.Repository.*;
import com.skillsync.Service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobSkillRepository jobSkillRepository;
    private final CandidateSkillRepository candidateSkillRepository;
    private final MatchResultRepository matchResultRepository;

    @Override
    public List<MatchResultDTO> matchCandidateForJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job Not Found"));

        List<JobSkill> requiredSkills = jobSkillRepository.findByJobId(jobId);

        List<User> candidates = userRepository.findAll()
                .stream()
                .filter(u -> u.getRole().name().equals("CANDIDATE"))
                .toList();

        List<MatchResultDTO> results = new ArrayList<>();

        for(User candidate : candidates){
            List<CandidateSkill> cSkills = candidateSkillRepository.findByCandidateId(candidate.getId());
            double score = calculateScore(requiredSkills, cSkills);

            matchResultRepository.save(
                    MatchResult.builder()
                            .job(job)
                            .candidate(candidate)
                            .matchScore((int) Math.round(score))
                            .build()
            );


            MatchResultDTO dto = new MatchResultDTO();
            dto.setCandidateId(candidate.getId());
            dto.setJobId(jobId);
            dto.setScore(score);

            results.add(dto);
        }

        return results.stream()
                .sorted(Comparator.comparingDouble(MatchResultDTO::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchResultDTO> matchJobsForCandidate(Long candidateId) {
        User candidate = userRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        List<CandidateSkill> cSkills = candidateSkillRepository.findByCandidateId(candidateId);

        List<Job> jobs = jobRepository.findAll();

        List<MatchResultDTO> results = new ArrayList<>();

        for (Job job : jobs) {
            List<JobSkill> requiredSkills = jobSkillRepository.findByJobId(job.getId());

            double score = calculateScore(requiredSkills, cSkills);

            matchResultRepository.save(
                    MatchResult.builder()
                            .job(job)
                            .candidate(candidate)
                            .matchScore((int) Math.round(score))
                            .build()
            );

            MatchResultDTO dto = new MatchResultDTO();
            dto.setCandidateId(candidateId);
            dto.setJobId(job.getId());
            dto.setScore(score);

            results.add(dto);
        }

        return results.stream()
                .sorted(Comparator.comparingDouble(MatchResultDTO::getScore).reversed())
                .collect(Collectors.toList());
    }

    private double calculateScore(List<JobSkill> req, List<CandidateSkill> cand) {

        double total = 0;

        for (JobSkill js : req) {
            int requiredLevel = js.getRequiredLevel();
            int candidateLevel = cand.stream()
                    .filter(c -> c.getSkill().getId().equals(js.getSkill().getId()))
                    .map(CandidateSkill::getLevel)
                    .findFirst()
                    .orElse(0);

            double match = Math.min(
                    (double) candidateLevel / requiredLevel,
                    1.0
            );

            total += match;
        }

        return req.isEmpty() ? 0 : (total / req.size()) * 100;
    }
}
