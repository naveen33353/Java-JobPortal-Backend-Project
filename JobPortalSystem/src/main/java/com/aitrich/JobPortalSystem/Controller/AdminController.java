package com.aitrich.JobPortalSystem.Controller;



import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Service.Admin.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    // JobSeekers

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/jobseekers")
    public ResponseEntity<List<JobSeekerResponseDTO>> getAllJobSeekers() {
        return ResponseEntity.ok(adminService.getAllJobSeekers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/jobseeker/{id}")
    public ResponseEntity<String> deleteJobSeeker(@PathVariable Long id) {
        adminService.deleteJobSeeker(id);
        return ResponseEntity.ok("JobSeeker deleted successfully");
    }

    //Jobs

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
        return ResponseEntity.ok(adminService.getAllJobs());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        adminService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
