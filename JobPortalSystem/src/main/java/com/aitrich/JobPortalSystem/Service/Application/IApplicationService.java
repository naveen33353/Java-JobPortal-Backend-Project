package com.aitrich.JobPortalSystem.Service.Application;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.DTO.ApplicationResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Application;

import java.util.List;

public interface IApplicationService {

    public ApplicationPostDTO postApplication(ApplicationPostDTO applicationPostDTO);

    public ApplicationResponseDTO getApplicationById(long id);

    public List<ApplicationResponseDTO> getAllApplications();

    public ApplicationResponseDTO updateApplication(ApplicationPostDTO applicationDTO , Long id);

    public void deleteApplicationById(long id);

    public List<ApplicationResponseDTO> searchApplicationByJobId(Long id);

    public List<ApplicationResponseDTO> searchApplicationByJobSeekerId(Long id);

    public ApplicationPostDTO setStatus(String  status , Long id);

    public List<ApplicationResponseDTO> getApprovedApplications();
}