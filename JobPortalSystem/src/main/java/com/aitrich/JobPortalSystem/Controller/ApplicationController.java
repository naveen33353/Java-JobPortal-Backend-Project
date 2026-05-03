package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.DTO.ApplicationResponseDTO;
import com.aitrich.JobPortalSystem.Service.Application.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationPostDTO> postApplication(@RequestBody ApplicationPostDTO applicationPostDTO) {
        return ResponseEntity.ok(applicationService.postApplication(applicationPostDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateApplication(@RequestBody ApplicationPostDTO applicationDTO, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.updateApplication(applicationDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.ok("Deleted successfully");    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByJobId(@PathVariable long jobId) {
        return ResponseEntity.ok(applicationService.searchApplicationByJobId(jobId));
    }

    @GetMapping("/jobseeker/{jobId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationByJobSeekerId(@PathVariable long jobId){
        return ResponseEntity.ok(applicationService.searchApplicationByJobSeekerId(jobId));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<ApplicationPostDTO> setStatus(@PathVariable String status, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.setStatus(status , id));
    }

    @GetMapping("/approved")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByApproved() {
        return ResponseEntity.ok(applicationService.getApprovedApplications());
    }
}