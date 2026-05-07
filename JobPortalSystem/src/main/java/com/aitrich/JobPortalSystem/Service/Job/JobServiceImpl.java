package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Repository.ICompanyRepo;
import com.aitrich.JobPortalSystem.Repository.IJobRepo;
import com.aitrich.JobPortalSystem.Repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements IJobService {

    private final IJobRepo jobRepo;
    private final ICompanyRepo companyRepo;


    @Override
    public JobDTO createJob(JobDTO jobDto) {

        Company company = companyRepo.findById(jobDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Job job = new Job();
        job.setCompany(company);
        job.setDescription(jobDto.getDescription());
        job.setSkills(jobDto.getSkills());
        job.setExperience(jobDto.getExperience());
        job.setSalary(jobDto.getSalary());
        job.setPostedDate(LocalDate.now());
        job.setEndDate(jobDto.getEndDate());

        Job savedJob = jobRepo.save(job);
        return mapToDTO(savedJob);
    }


    @Override
    public JobDTO getJobById(long id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return mapToDTO(job);
    }


    @Override
    public List<JobDTO> listAllJob() {
        return jobRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    @Override
    public JobDTO updateJob(long id, JobDTO jobDto) {

        Job existing = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Company company = companyRepo.findById(jobDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        existing.setCompany(company);
        existing.setDescription(jobDto.getDescription());
        existing.setSkills(jobDto.getSkills());
        existing.setExperience(jobDto.getExperience());
        existing.setSalary(jobDto.getSalary());
        existing.setEndDate(jobDto.getEndDate());

        jobRepo.save(existing);

        return mapToDTO(existing);
    }


    @Override
    public void deleteJobById(long id) {
        jobRepo.deleteById(id);
    }


    private JobDTO mapToDTO(Job job) {
        JobDTO dto = new JobDTO();

        dto.setJobId(job.getJobId());
        dto.setCompanyId(job.getCompany().getId());
        dto.setCompanyName(job.getCompany().getCompanyName());
        dto.setDescription(job.getDescription());
        dto.setPostedDate(job.getPostedDate());
        dto.setEndDate(job.getEndDate());
        dto.setSkills(job.getSkills());
        dto.setExperience(job.getExperience());
        dto.setSalary(job.getSalary());

        return dto;
    }
}