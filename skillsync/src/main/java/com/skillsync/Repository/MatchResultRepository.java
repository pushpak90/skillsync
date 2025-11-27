package com.skillsync.Repository;

import com.skillsync.Entity.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
    List<MatchResult> findByJobId(Long jobId);
    List<MatchResult> findByCandidateId(Long candidateId);
}
