package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Exception.ResourceNotFoundException;
import com.aitrich.JobPortalSystem.Service.Job.IJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor

public class JobController {

    private final IJobService jobService;


    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(
            @Valid @RequestBody JobRequestDTO jobDto) {

        JobResponseDTO response = jobService.createJob(jobDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(
            @PathVariable long id) {
        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
            return ResponseEntity.ok(jobDTO);

    }


    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {

        return ResponseEntity.ok(jobService.listAllJob());
    }


    @PreAuthorize("hasRole('COMPANY')")
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable long id,
            @Valid @RequestBody JobRequestDTO jobDto) {

        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
            return ResponseEntity.ok(jobService.updateJob(id, jobDto));

    }


    @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable long id) {
        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }else {
            jobService.deleteJobById(id);
            return ResponseEntity.ok("Job deleted successfully");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<JobResponseDTO>> searchJob(
            @RequestParam String keyword) {

        List<JobResponseDTO> jobDTO = jobService.searchJob(keyword);

            return ResponseEntity.ok(jobDTO);

    }


    @PreAuthorize("hasRole('JOBSEEKER')")
    @PostMapping("/{jobId}/save/{jobSeekerId}")
    public ResponseEntity<String> saveJobToProfile(
            @PathVariable Long jobId,
            @PathVariable Long jobSeekerId) {

        jobService.saveAJobToProfile(jobId, jobSeekerId);

        return ResponseEntity.ok("Job saved successfully");
    }


    @PreAuthorize("hasRole('JOBSEEKER')")
    @DeleteMapping("/{jobId}/unsave/{jobSeekerId}")
    public ResponseEntity<String> removeSavedJob(
            @PathVariable Long jobId,
            @PathVariable Long jobSeekerId) {

        jobService.removeSavedJobFromProfile(jobId, jobSeekerId);

        return ResponseEntity.ok("Saved job removed successfully");
    }


    @PreAuthorize("hasRole('JOBSEEKER')")
    @GetMapping("/saved/{jobSeekerId}")
    public ResponseEntity<List<JobResponseDTO>> getSavedJobs(
            @PathVariable Long jobSeekerId) {

        return ResponseEntity.ok(
                jobService.getSavedJobFromProfile(jobSeekerId)
        );
    }
}