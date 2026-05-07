package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Application;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Service.Job.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobServiceImpl jobService;

    // 🔥 CREATE
    @PostMapping
    public ResponseEntity<JobDTO> postJob(@RequestBody JobDTO jobDto) {
        return ResponseEntity.ok(jobService.createJob(jobDto));
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    // 🔥 GET ALL
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.listAllJob());
    }

    // 🔥 UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable long id,
                                            @RequestBody JobDTO jobDto) {
        return ResponseEntity.ok(jobService.updateJob(id, jobDto));
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable long id) {
        jobService.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }
}