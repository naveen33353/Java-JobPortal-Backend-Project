package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Exceptions.NotFoundException;
import com.aitrich.JobPortalSystem.Service.Job.JobServiceImpl;
import com.aitrich.JobPortalSystem.Service.JobSeeker.IJobSeekerService;
import com.aitrich.JobPortalSystem.Service.JobSeeker.JobSeekerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Job")
public class JobController {

    private final JobServiceImpl jobService;
    private final JobSeekerServiceImp jobSeekerService;

    @PostMapping("/")
    public ResponseEntity<JobDTO> postJob(@RequestBody JobDTO jobDto){
        return ResponseEntity.ok(jobService.createJob(jobDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.listAllJob());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable long id, @RequestBody JobDTO job) {

        return ResponseEntity.ok(jobService.updateJob(id,job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable long id) {
        jobService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/jobs/search")
    public ResponseEntity<List<Job>> searchJob(@RequestParam String keyword) {
        try {
            return ResponseEntity.ok(jobService.searchJob(keyword));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @PostMapping("/save/{jobSeekerId}/{jobId}")
    public ResponseEntity<String> savedJob(@PathVariable Long jobSeekerId,@PathVariable Long jobId) {

        jobService.savedJobs(jobSeekerId,jobId);
        return ResponseEntity.ok("Job saved successfully");
    }

    @PostMapping("/removeJob/{jobSeekerId}/{jobId}")
    public ResponseEntity<String> removeSavedJob(@PathVariable Long jobSeekerId ,@PathVariable Long jobId){

        jobService.removeSavedJob(jobSeekerId,jobId);
        return ResponseEntity.ok("Job removed successfully");
    }

    @GetMapping("/{jobSeekerId}/all-saved-job")
    public ResponseEntity<List<Job>> getSavedJob(@PathVariable Long jobSeekerId){
       return ResponseEntity.ok( jobService.getSavedJob(jobSeekerId));
    }



}
