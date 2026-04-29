package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Job;

import java.util.List;

public interface IJobService {

    public JobDTO createJob(JobDTO jobDto);

    public void deleteJobById(long id);

    public JobDTO getJobById(long id);

    public List<JobDTO> listAllJob();

    public JobDTO updateJob(long id, JobDTO updatedJob);
}

