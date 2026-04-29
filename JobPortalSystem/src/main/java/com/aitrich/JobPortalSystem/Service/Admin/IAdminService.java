package com.aitrich.JobPortalSystem.Service.Admin;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;

import java.util.List;

public interface IAdminService {
    List<JobSeekerResponseDTO> getAllJobSeekers();

    void deleteJobSeeker(Long id);

    List<JobDTO>getAllJobs();


    public void deleteJob(Long id);

}
