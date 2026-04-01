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
@RequestMapping("/Job")
public class JobController {

    private final JobServiceImpl jobSerive;

    @PostMapping("/")
    public ResponseEntity<JobDTO> postJob(@RequestBody JobDTO jobDto){
        return ResponseEntity.ok(jobSerive.createJob(jobDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id) {
        return ResponseEntity.ok(jobSerive.getJobById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobSerive.listAllJob());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable long id, @RequestBody JobDTO job) {

        return ResponseEntity.ok(jobSerive.updateJob(id,job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable long id) {
        jobSerive.deleteJobById(id);
        return ResponseEntity.noContent().build();
    }

}
