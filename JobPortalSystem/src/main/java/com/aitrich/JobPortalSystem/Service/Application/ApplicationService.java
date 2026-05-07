package com.aitrich.JobPortalSystem.Service.Application;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.Entity.Application;
import com.aitrich.JobPortalSystem.Enums.Status;
import com.aitrich.JobPortalSystem.Repository.IApplicationRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationService {

    private final IApplicationRepo applicationRepo;
    private final ModelMapper modelMapper;

    @Override
    public ApplicationPostDTO postApplication(ApplicationPostDTO applicationPostDTO) {
        applicationPostDTO.setAppliedDate(LocalDate.now());
        Application application = modelMapper.map(applicationPostDTO, Application.class);
        applicationRepo.save(application);
        ApplicationPostDTO response = modelMapper.map(application, ApplicationPostDTO.class);
        return response;
    }

    @Override
    public Application getApplicationById(long id) {
        return applicationRepo.findById(id).orElseThrow(()-> new RuntimeException("Application with id " + id + " Not Found!"));
    }

    public List<Application> getAllApplications() {

        return applicationRepo.findAll();
    }

    @Override
    public void deleteApplicationById(long id) {
        applicationRepo.deleteById(id);
    }

    @Override
    public Application updateApplication(ApplicationPostDTO applicationDTO , Long id) {
        Application existingApplication = applicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No application found!"));
        modelMapper.map(applicationDTO, existingApplication);
        return applicationRepo.save(existingApplication);
    }

    @Override
    public List<Application> searchApplicationByJobSeekerId(Long id) {

        List<Application> applications = applicationRepo.findByJobSeeker_Id(id);

        if (applications.isEmpty()) {
            throw new RuntimeException("No Application found with job seeker id " + id);
        }

        return applications;
    }

    @Override
    public List<Application> searchApplicationByJobId(Long id) {

        List<Application> applications = applicationRepo.findByJob_JobId(id);

        if (applications.isEmpty()) {
            throw new RuntimeException("No Application found with job id " + id);
        }

        return applications;
    }

    @Override
    public ApplicationPostDTO setStatus(String status , Long id) {
        Application application = applicationRepo.findById(id).orElseThrow(()-> new RuntimeException("Application with id " + id + " Not Found!"));
        application.setStatus(Enum.valueOf(Status.class,status));
        applicationRepo.save(application);
        ApplicationPostDTO response = modelMapper.map(application, ApplicationPostDTO.class);
        return response;
    }

    @Override
    public List<Application> getApprovedApplications() {
        return applicationRepo.findByStatus();
    }
}