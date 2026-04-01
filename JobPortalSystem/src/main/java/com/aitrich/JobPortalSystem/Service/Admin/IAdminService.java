package com.aitrich.JobPortalSystem.Service.Admin;

import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;

import java.util.List;

public interface IAdminService {
    List<JobSeekerResponseDTO> getAllJobSeekers();

    void deleteJobSeeker(Long id);

    List<Job>getAllJobs();


    public void deleteJob(Long id);

}
