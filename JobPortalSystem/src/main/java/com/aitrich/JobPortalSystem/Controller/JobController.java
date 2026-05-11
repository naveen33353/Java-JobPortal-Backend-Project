package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Service.Job.IJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor

public class JobController {

    private final IJobService jobService;


    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(
            @RequestBody JobRequestDTO jobDto) {

        JobResponseDTO response = jobService.createJob(jobDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(
            @PathVariable long id) {
        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            return  ResponseEntity.status(404).body("job not found!!!");
        }
            return ResponseEntity.ok(jobDTO);

    }


    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {

        return ResponseEntity.ok(jobService.listAllJob());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(
            @PathVariable long id,
            @RequestBody JobRequestDTO jobDto) {

        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            return  ResponseEntity.status(404).body("job not found!!!");
        }
            return ResponseEntity.ok(jobService.updateJob(id, jobDto));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable long id) {
        JobResponseDTO jobDTO = jobService.getJobById(id);
        if(jobDTO == null){
            return  ResponseEntity.status(404).body("job not found!!!");
        }else {
            jobService.deleteJobById(id);
            return ResponseEntity.ok("Job deleted successfully");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<JobResponseDTO>> searchJob(
            @RequestParam String keyword) {

        List<JobResponseDTO> jobDTO = jobService.searchJob(keyword);

        if(jobDTO.isEmpty()){
            return ResponseEntity.status(404).body(Collections.emptyList());
        }

            return ResponseEntity.ok(jobDTO);

    }


    @PostMapping("/{jobId}/save/{jobSeekerId}")
    public ResponseEntity<String> saveJobToProfile(
            @PathVariable Long jobId,
            @PathVariable Long jobSeekerId) {

        jobService.saveAJobToProfile(jobId, jobSeekerId);

        return ResponseEntity.ok("Job saved successfully");
    }


    @DeleteMapping("/{jobId}/unsave/{jobSeekerId}")
    public ResponseEntity<String> removeSavedJob(
            @PathVariable Long jobId,
            @PathVariable Long jobSeekerId) {

        jobService.removeSavedJobFromProfile(jobId, jobSeekerId);

        return ResponseEntity.ok("Saved job removed successfully");
    }


    @GetMapping("/saved/{jobSeekerId}")
    public ResponseEntity<List<JobResponseDTO>> getSavedJobs(
            @PathVariable Long jobSeekerId) {

        return ResponseEntity.ok(
                jobService.getSavedJobFromProfile(jobSeekerId)
        );
    }
}