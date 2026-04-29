package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Exceptions.NotFoundException;
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
    private final ModelMapper modelMapper;
    private final IJobSeekerRepo jobSeekerRepo;

    @Override
    public JobDTO createJob(JobDTO jobDto){
        Job job = modelMapper.map(jobDto, Job.class);
        job.setPostedDate(LocalDate.from(LocalDate.now()));
         jobRepo.save(job);
         JobDTO response = modelMapper.map(job, JobDTO.class);
         return response;
    }

    @Override
    public void deleteJobById(long id){

        Job job = jobRepo.findById(id)
                        .orElseThrow(() -> new NotFoundException("Job not found with id :" + id));
         jobRepo.deleteById(id);
    }

    @Override
    public Job getJobById(long id){
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found with id :" + id));
     return jobRepo.getById(id);
    }


    @Override
    public List<Job> listAllJob(){

        return jobRepo.findAll();
    }


    @Override
    public Job updateJob(long id, JobDTO updatedJob){

        Job job = modelMapper.map(updatedJob, Job.class);
        return jobRepo.save(job);
    }

    @Override
    public List<Job> searchJob(String keyword){

        return jobRepo.searchJobs(keyword);
    }

    @Override
    public void savedJobs(Long jobId, Long jobSeekerId){
        JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new NotFoundException("JobSeeker not found with id"+jobSeekerId));
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not Found with id "+jobId));

        if(!jobSeeker.getSavedJobs().contains(job)){
           jobSeeker.getSavedJobs().add(job);
        }
    }

    @Override
    public void removeSavedJob(Long jobId,Long jobSeekerId){
        JobSeeker jobseeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new NotFoundException("JobSeeker not found with id "+jobSeekerId ));

        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found with id " + jobId));
        jobseeker.getSavedJobs().remove(job);
    }

    @Override
    public List<Job> getSavedJob(Long jobSeekerId){

         JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
                .orElseThrow(() -> new NotFoundException("JobSeeker not found with id" + jobSeekerId));

        return jobSeeker.getSavedJobs();
    }

}
