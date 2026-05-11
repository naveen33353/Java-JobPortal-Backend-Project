package com.aitrich.JobPortalSystem.Service.Admin;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;

import java.util.List;

public interface IAdminService {
    List<JobSeekerResponseDTO> getAllJobSeekers();

    void deleteJobSeeker(Long id);

    public List<JobResponseDTO>getAllJobs();


    public void deleteJob(Long id);

}
