package com.skillsync.Service.Impl;

import com.skillsync.DTO.JobDTO;
import com.skillsync.DTO.JobSkillDTO;
import com.skillsync.Entity.Job;
import com.skillsync.Entity.JobSkill;
import com.skillsync.Entity.Skill;
import com.skillsync.Entity.User;
import com.skillsync.Repository.JobRepository;
import com.skillsync.Repository.JobSkillRepository;
import com.skillsync.Repository.SkillRepository;
import com.skillsync.Repository.UserRepository;
import com.skillsync.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final JobSkillRepository jobSkillRepository;

    @Override
    public JobDTO createJob(JobDTO dto) {
        User employee = userRepository.findById(dto.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        Job job = Job.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .employer(employee)
                .build();
        Job saved = jobRepository.save(job);

        List<JobSkill> requiredSkills = new ArrayList<>();

        for(JobSkillDTO js : dto.getRequiredSkills()){
            Skill skill = skillRepository.findById(js.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill Not Found"));
            JobSkill jobSkill = JobSkill.builder()
                    .job(saved)
                    .skill(skill)
                    .requiredLevel(js.getRequiredLevel())
                    .build();

            requiredSkills.add(jobSkillRepository.save(jobSkill));
        }
        dto.setId(saved.getId());

        return dto;
    }

    @Override
    public List<JobDTO> getAllJob() {
        return List.of();
    }

    @Override
    public JobDTO getJobById(Long id) {
        return null;
    }
}
