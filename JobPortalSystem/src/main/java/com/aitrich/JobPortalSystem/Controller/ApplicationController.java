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
@RequestMapping("/Application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/")
    public ResponseEntity<ApplicationPostDTO> postApplication(@RequestBody ApplicationPostDTO applicationPostDTO) {
        return ResponseEntity.ok(applicationService.postApplication(applicationPostDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@RequestBody ApplicationPostDTO applicationDTO, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.updateApplication(applicationDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable long jobId) {
        return ResponseEntity.ok(applicationService.searchApplicationByJobId(jobId));
    }

    @GetMapping("/jobseeker/{jbsId}")
    public ResponseEntity<List<Application>> getApplicationByJobSeekerId(@PathVariable long jbsId){
        return ResponseEntity.ok(applicationService.searchApplicationByJobSeekerId(jbsId));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<ApplicationPostDTO> setStatus(@PathVariable String status, @PathVariable long id) {
        return ResponseEntity.ok(applicationService.setStatus(status , id));
    }
}