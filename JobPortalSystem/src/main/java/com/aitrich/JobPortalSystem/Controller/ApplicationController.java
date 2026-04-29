package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.Entity.Application;
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

    @PostMapping("/api/applications")
    public ResponseEntity<ApplicationPostDTO> postApplication(@RequestBody ApplicationPostDTO applicationPostDTO) {
        return ResponseEntity.ok(applicationService.postApplication(applicationPostDTO));
    }

    @GetMapping("/api/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping("/api/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PutMapping("/api/applications/{id}")
    public ResponseEntity<Application> updateApplication(@RequestBody ApplicationPostDTO applicationDTO, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.updateApplication(applicationDTO,id));
    }

    @DeleteMapping("/api/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/applications/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable long jobId) {
        return ResponseEntity.ok(applicationService.searchApplicationByJobId(jobId));
    }

    @GetMapping("/api/applications/jobseeker/{jobId}")
    public ResponseEntity<List<Application>> getApplicationByJobSeekerId(@PathVariable long jobId){
        return ResponseEntity.ok(applicationService.searchApplicationByJobSeekerId(jobId));
    }

    @PutMapping("/api/applications/{id}/{status}")
    public ResponseEntity<ApplicationPostDTO> setStatus(@PathVariable String status, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.setStatus(status , id));
    }
}