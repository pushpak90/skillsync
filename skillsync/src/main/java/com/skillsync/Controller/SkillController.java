package com.skillsync.Controller;

import com.skillsync.DTO.SkillDTO;
import com.skillsync.Service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillDTO dto){
        return ResponseEntity.ok(skillService.createSkill(dto));
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills(){
        return ResponseEntity.ok(skillService.getSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id){
        return ResponseEntity.ok(skillService.getSkillById(id));
    }
}
