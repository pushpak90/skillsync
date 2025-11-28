package com.skillsync.Service;

import com.skillsync.DTO.JobDTO;

import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO dto);
    List<JobDTO> getAllJob();
    JobDTO getJobById(Long id);
}
