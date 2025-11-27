package com.skillsync.DTO;

import lombok.Data;

@Data
public class MatchResultDTO {
    private Long jobId;
    private Long candidateId;
    private int score;
}
