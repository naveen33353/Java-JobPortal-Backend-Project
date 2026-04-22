package com.aitrich.JobPortalSystem.Controller;



import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Service.Admin.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    // JobSeekers

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/jobseekers")
    public ResponseEntity<List<JobSeekerResponseDTO>> getAllJobSeekers() {
        return ResponseEntity.ok(adminService.getAllJobSeekers());
    }

    @DeleteMapping("/api/admin/jobseeker/{id}")
    public ResponseEntity<String> deleteJobSeeker(@PathVariable Long id) {
        adminService.deleteJobSeeker(id);
        return ResponseEntity.ok("JobSeeker deleted successfully");
    }

    //Jobs

    @GetMapping("/api/admin/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(adminService.getAllJobs());
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        adminService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
