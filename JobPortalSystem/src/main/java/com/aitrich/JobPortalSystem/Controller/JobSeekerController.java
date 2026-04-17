package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobSeekerRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Service.JobSeeker.JobSeekerServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jobseekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerServiceImp service;

    @PostMapping
    public ResponseEntity<JobSeekerResponseDTO> create(@Valid @RequestBody JobSeekerRequestDTO dto) {
        return ResponseEntity.ok(service.createJobSeeker(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getJobSeekerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeekerResponseDTO> update(
            @PathVariable Long id,
            @RequestBody JobSeekerRequestDTO dto) {
        return ResponseEntity.ok(service.updateJobSeeker(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteJobSeeker(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("/{id}/uploadResume")
    public ResponseEntity<String>uploadResume(@PathVariable Long id,
                                              @RequestParam("file") MultipartFile file){
        service.uploadResume(id,file);
        return ResponseEntity.ok("Resume uploaded successfully");
    }

    @DeleteMapping("/{id}/resume")
    public ResponseEntity<String> deleteResume(@PathVariable Long id) {

        service.deleteResume(id);

        return ResponseEntity.ok("Resume deleted successfully");
    }
}
