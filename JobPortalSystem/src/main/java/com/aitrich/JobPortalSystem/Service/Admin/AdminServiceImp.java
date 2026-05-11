package com.aitrich.JobPortalSystem.Service.Admin;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Service.Company.ICompanyService;
import com.aitrich.JobPortalSystem.Service.Job.IJobService;
import com.aitrich.JobPortalSystem.Service.JobSeeker.IJobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements IAdminService{
    private final IJobSeekerService jobSeekerService;
    private final ICompanyService companyService;
    private final IJobService jobService;

    @Override
    public List<JobSeekerResponseDTO> getAllJobSeekers(){
        return jobSeekerService.getAllJobSeekers();
    }

    @Override
    public void deleteJobSeeker(Long id){
        jobSeekerService.deleteJobSeeker(id);
    }

    //----I need to have company authorization methods----

    @Override
    public List<JobResponseDTO>getAllJobs(){
        return jobService.listAllJob();
    }

    @Override
    public void deleteJob(Long id){
        jobService.deleteJobById(id);
    }

}
