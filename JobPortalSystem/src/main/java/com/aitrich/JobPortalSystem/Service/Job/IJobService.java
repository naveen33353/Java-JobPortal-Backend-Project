package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;

import java.util.List;

public interface IJobService {

    public JobResponseDTO createJob(JobRequestDTO jobDto);

    public void deleteJobById(long id);

    public JobResponseDTO getJobById(long id);

    public List<JobResponseDTO> listAllJob();

    public JobResponseDTO updateJob(long id, JobRequestDTO updatedJob);

    public List<JobResponseDTO > searchJob(String keyword);

    public void saveAJobToProfile(Long jobId, Long jobSeekerId);

    public void removeSavedJobFromProfile(Long jobId, Long jobSeekerId);

    public List<JobResponseDTO> getSavedJobFromProfile(Long jobSeekerId);
}

