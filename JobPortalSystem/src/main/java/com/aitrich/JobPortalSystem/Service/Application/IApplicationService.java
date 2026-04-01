package com.aitrich.JobPortalSystem.Service.Application;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.Entity.Application;

import java.util.List;

public interface IApplicationService {

    public ApplicationPostDTO postApplication(ApplicationPostDTO applicationPostDTO);

    public Application getApplicationById(long id);

    public List<Application> getAllApplications();

    public Application updateApplication(ApplicationPostDTO applicationDTO , Long id);

    public void deleteApplicationById(long id);

    public List<Application> searchApplicationByJobId(Long id);

    public List<Application> searchApplicationByJobSeekerId(Long id);

    public ApplicationPostDTO setStatus(String  status , Long id);

    public List<Application> getApprovedApplications();
}