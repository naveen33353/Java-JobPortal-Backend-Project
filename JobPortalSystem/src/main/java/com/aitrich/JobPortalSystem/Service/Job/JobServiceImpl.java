package com.aitrich.JobPortalSystem.Service.Job;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Repository.IJobRepo;
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
    private final ModelMapper modelMapper;

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
         jobRepo.deleteById(id);
    }

    @Override
    public Job getJobById(long id){
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
}
