package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Repository.ICompanyRepo;
import com.aitrich.JobPortalSystem.Repository.IJobRepo;
import com.aitrich.JobPortalSystem.Repository.IJobSeekerRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements IJobService {

    private final IJobRepo jobRepo;
    private final ICompanyRepo companyRepo;
    private final ModelMapper modelMapper;
    private final IJobSeekerRepo jobSeekerRepo;


    @Override
    public JobResponseDTO createJob(JobRequestDTO jobDto) {

        Job job = modelMapper.map(jobDto, Job.class);
        job.setPostedDate(LocalDate.now());
        Company company = companyRepo.findById(jobDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException( "company not found"));
        job.setCompany(company);
        jobRepo.save(job);

        JobResponseDTO response = modelMapper.map(job, JobResponseDTO.class);
        response.setCompanyName(company.getCompanyName());

        return response;
    }


    @Override
    public JobResponseDTO getJobById(long id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        Company company = companyRepo.findById(job.getCompany().getId())
                .orElseThrow(() -> new RuntimeException( "company not found"));

        JobResponseDTO jobDTO =modelMapper.map(job, JobResponseDTO.class);
        jobDTO.setCompanyName(company.getCompanyName());


        return jobDTO;
    }


    @Override
    public List<JobResponseDTO> listAllJob() {
        return jobRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    @Override
    public JobResponseDTO updateJob(long id, JobRequestDTO jobDto) {

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

        return convertToDTO(existing);
    }


    @Override
    public void deleteJobById(long id) {
        jobRepo.deleteById(id);
    }


    private JobResponseDTO convertToDTO(Job job) {
        JobResponseDTO dto = modelMapper.map(job, JobResponseDTO.class);

        if (job.getCompany() != null) {

            dto.setCompanyName(job.getCompany().getCompanyName());
            return dto;
        }else {
            return null;
        }
    }

    @Override
    public List<JobResponseDTO> searchJob(String keyword){

    return  jobRepo.searchJobs(keyword)
                        .stream()
                        .map(this::convertToDTO)
                        .toList();
    }

    @Override
    public void saveAJobToProfile(Long jobId, Long jobSeekerId){
        JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id"+jobSeekerId));
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not Found with id "+jobId));

        if(!jobSeeker.getSavedJobs().contains(job)){
            jobSeeker.getSavedJobs().add(job);
        }
    }

    @Override
    public void removeSavedJobFromProfile(Long jobId, Long jobSeekerId){
        JobSeeker jobseeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id "+jobSeekerId ));

        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id " + jobId));
        jobseeker.getSavedJobs().remove(job);
    }

    @Override
    public List<JobResponseDTO> getSavedJobFromProfile(Long jobSeekerId){

        JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id" + jobSeekerId));

         return  jobSeeker.getSavedJobs()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}

