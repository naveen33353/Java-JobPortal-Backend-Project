package com.aitrich.JobPortalSystem.Service.Application;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.DTO.ApplicationResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Application;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Enums.Status;
import com.aitrich.JobPortalSystem.Repository.IApplicationRepo;
import com.aitrich.JobPortalSystem.Repository.IJobRepo;
import com.aitrich.JobPortalSystem.Repository.IJobSeekerRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationService {

    private final IApplicationRepo applicationRepo;
    private final IJobRepo jobRepo;
    private final IJobSeekerRepo jobSeekerRepo;
    private final ModelMapper modelMapper;

    @Override
    public ApplicationPostDTO postApplication(ApplicationPostDTO dto) {

        Application application = new Application();

        application.setAppliedDate(LocalDate.now());
        application.setStatus(dto.getStatus());

        if (dto.getJobSeekerId() == null) {
            throw new RuntimeException("JobSeeker ID is required");
        }

        JobSeeker jobSeeker = jobSeekerRepo.findById(dto.getJobSeekerId())
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        application.setJobSeeker(jobSeeker);

        if (dto.getJobId() != null) {
            Job job = jobRepo.findById(dto.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found"));
            application.setJob(job);
        }

        applicationRepo.save(application);

        dto.setAppliedDate(application.getAppliedDate());
        return dto;
    }

    @Override
    public ApplicationResponseDTO getApplicationById(long id) {
        Application application =  applicationRepo.findById(id).orElse(null);
        ApplicationResponseDTO applicationResponseDTO = new ApplicationResponseDTO();
        applicationResponseDTO.setId(application.getId());
        if (application.getJobSeeker() != null) {
            applicationResponseDTO.setJobSeekerId(application.getJobSeeker().getId());
        }
        if (application.getJob() != null) {
            applicationResponseDTO.setJobId(application.getJob().getJobId());
        }
        applicationResponseDTO.setStatus(application.getStatus());
        applicationResponseDTO.setAppliedDate(application.getAppliedDate());

        return applicationResponseDTO;
    }

    public List<ApplicationResponseDTO> getAllApplications() {
        List<Application> applications = applicationRepo.findAll();

        List<ApplicationResponseDTO> responseList = new ArrayList<>();

        for (Application application : applications) {
            ApplicationResponseDTO dto = new ApplicationResponseDTO();

            dto.setId(application.getId());

            if (application.getJobSeeker() != null) {
                dto.setJobSeekerId(application.getJobSeeker().getId());
            }

            if (application.getJob() != null) {
                dto.setJobId(application.getJob().getJobId());
            }

            dto.setStatus(application.getStatus());
            dto.setAppliedDate(application.getAppliedDate());

            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public void deleteApplicationById(long id) {
        applicationRepo.deleteById(id);
    }

    @Override
    public ApplicationResponseDTO updateApplication(ApplicationPostDTO dto, Long id) {

        Application app = applicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        app.setStatus(dto.getStatus());

        applicationRepo.save(app);

        ApplicationResponseDTO res = new ApplicationResponseDTO();
        res.setId(app.getId());
        res.setStatus(app.getStatus());
        res.setAppliedDate(app.getAppliedDate());

        return res;
    }

    @Override
    public List<ApplicationResponseDTO> searchApplicationByJobSeekerId(Long id) {

        List<Application> applications = applicationRepo.findByJobSeeker_Id(id);

        if (applications.isEmpty()) {
            throw new RuntimeException("No Application found with job seeker id " + id);
        }

        List<ApplicationResponseDTO> responseList = new ArrayList<>();

        for (Application app : applications) {
            ApplicationResponseDTO dto = new ApplicationResponseDTO();

            dto.setId(app.getId());
            dto.setJobSeekerId(app.getJobSeeker().getId());

            if (app.getJob() != null) {
                dto.setJobId(app.getJob().getJobId());
            }

            dto.setStatus(app.getStatus());
            dto.setAppliedDate(app.getAppliedDate());

            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public List<ApplicationResponseDTO> searchApplicationByJobId(Long id) {

        List<Application> applications = applicationRepo.findByJob_JobId(id);

        if (applications.isEmpty()) {
            throw new RuntimeException("No Application found with job id " + id);
        }

        List<ApplicationResponseDTO> responseList = new ArrayList<>();

        for (Application app : applications) {
            ApplicationResponseDTO dto = new ApplicationResponseDTO();

            dto.setId(app.getId());
            dto.setJobSeekerId(app.getJobSeeker().getId());

            if (app.getJob() != null) {
                dto.setJobId(app.getJob().getJobId());
            }

            dto.setStatus(app.getStatus());
            dto.setAppliedDate(app.getAppliedDate());

            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public ApplicationPostDTO setStatus(String status , Long id) {
        Application application = applicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application with id " + id + " Not Found!"));

        application.setStatus(Status.valueOf(status));
        applicationRepo.save(application);

        return modelMapper.map(application, ApplicationPostDTO.class);
    }

    @Override
    public List<ApplicationResponseDTO> getApprovedApplications() {

        List<Application> applications = applicationRepo.findByStatus();

        if (applications.isEmpty()) {
            throw new RuntimeException("No approved applications found");
        }

        List<ApplicationResponseDTO> responseList = new ArrayList<>();

        for (Application app : applications) {
            ApplicationResponseDTO dto = new ApplicationResponseDTO();

            dto.setId(app.getId());
            dto.setJobSeekerId(app.getJobSeeker().getId());

            if (app.getJob() != null) {
                dto.setJobId(app.getJob().getJobId());
            }

            dto.setStatus(app.getStatus());
            dto.setAppliedDate(app.getAppliedDate());

            responseList.add(dto);
        }

        return responseList;
    }
}