package com.aitrich.JobPortalSystem.Service.JobSeeker;

import com.aitrich.JobPortalSystem.DTO.JobSeekerRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IJobSeekerService {

    JobSeekerResponseDTO createJobSeeker(JobSeekerRequestDTO dto);

    JobSeekerResponseDTO getJobSeekerById(Long id);

    List<JobSeekerResponseDTO> getAllJobSeekers();

    JobSeekerResponseDTO updateJobSeeker(Long id, JobSeekerRequestDTO dto);

    void deleteJobSeeker(Long id);

    void uploadResume(Long id , MultipartFile file);

    void deleteResume(Long id);

}
