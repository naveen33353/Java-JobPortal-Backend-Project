package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobSeekerRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Service.JobSeeker.JobSeekerServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jobseekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerServiceImp service;

    @PostMapping
    public ResponseEntity<JobSeekerResponseDTO> create(@Valid @RequestBody JobSeekerRequestDTO dto) {
        return ResponseEntity.ok(service.createJobSeeker(dto));
    }

    @GetMapping("/api/jobseekers/{id}")
    public ResponseEntity<JobSeekerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getJobSeekerById(id));
    }

    @PutMapping(" /api/jobseekers/{id}")
    public ResponseEntity<JobSeekerResponseDTO> update(
            @PathVariable Long id,
            @RequestBody JobSeekerRequestDTO dto) {
        return ResponseEntity.ok(service.updateJobSeeker(id, dto));
    }

    @DeleteMapping(" /api/jobseekers/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteJobSeeker(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PreAuthorize("hasRole('JOBSEEKER')")
    @PostMapping(" /api/jobseekers/{id}/uploadResume")
    public ResponseEntity<String>uploadResume(@PathVariable Long id,
                                              @RequestParam("file") MultipartFile file){
        service.uploadResume(id,file);
        return ResponseEntity.ok("Resume uploaded successfully");
    }

    @DeleteMapping(" /api/jobseekers/{id}/resume")
    public ResponseEntity<String> deleteResume(@PathVariable Long id) {

        service.deleteResume(id);

        return ResponseEntity.ok("Resume deleted successfully");
    }
}
