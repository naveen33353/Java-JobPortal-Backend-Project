package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Job;

import java.util.List;

public interface IJobService {

    JobDTO createJob(JobDTO jobDto);

     void deleteJobById(long id);

     Job getJobById(long id);

     List<Job> listAllJob();

     Job updateJob(long id, JobDTO updatedJob);


     List<Job> searchJob(String keyword);

     void savedJobs(Long jobId, Long jobSeekerId);

     void removeSavedJob(Long jobId,Long jobSeekerId);

    List<Job> getSavedJob(Long jobSeekerId);
}

