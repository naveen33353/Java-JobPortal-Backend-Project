package com.aitrich.JobPortalSystem.Service.JobSeeker;

import com.aitrich.JobPortalSystem.DTO.JobSeekerRequestDTO;
import com.aitrich.JobPortalSystem.DTO.JobSeekerResponseDTO;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Repository.IJobSeekerRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImp implements IJobSeekerService {

    private final IJobSeekerRepo repository;
    private final ModelMapper modelMapper;

    @Override
    public JobSeekerResponseDTO createJobSeeker(JobSeekerRequestDTO dto) {

        JobSeeker jobSeeker = modelMapper.map(dto, JobSeeker.class);

        JobSeeker saved = repository.save(jobSeeker);

        return modelMapper.map(saved, JobSeekerResponseDTO.class);
    }

    @Override
    public JobSeekerResponseDTO getJobSeekerById(Long id) {
        JobSeeker js = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        return modelMapper.map(js, JobSeekerResponseDTO.class);
    }

    @Override
    public List<JobSeekerResponseDTO> getAllJobSeekers() {
        return repository.findAll()
                .stream()
                .map(js -> modelMapper.map(js, JobSeekerResponseDTO.class))
                .toList();
    }

    @Override
    public JobSeekerResponseDTO updateJobSeeker(Long id, JobSeekerRequestDTO dto) {

        JobSeeker js = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        js.setFirstName(dto.getFirstName());
        js.setLastName(dto.getLastName());
        js.setSkills(dto.getSkills());
        js.setLocation(dto.getLocation());

        JobSeeker updated = repository.save(js);

        return modelMapper.map(updated, JobSeekerResponseDTO.class);
    }

    @Override
    public void deleteJobSeeker(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void uploadResume(Long id , MultipartFile file){
        JobSeeker js=repository.findById(id).orElseThrow(
                ()->new RuntimeException("Jobseeker not found")
        );
        if(!file.getContentType().equals("application/pdf")){
            throw new RuntimeException("Only PDF allowed");
        }
        try{
            String uploadDir="uploads/resumes/";
            String fileName=id+"_"+file.getOriginalFilename();

            Path path=Paths.get(uploadDir+fileName);

            Files.createDirectories(path.getParent());
            Files.write(path,file.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);

            js.setResumeUrl(path.toString());
            repository.save(js);
        }catch (IOException e){
            throw new RuntimeException("File upload failed");
        }
    }

    @Override
    public void deleteResume(Long id) {

        JobSeeker js = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        try {
            if (js.getResumeUrl() != null) {
                Path path = Paths.get(js.getResumeUrl());
                Files.deleteIfExists(path);

                js.setResumeUrl(null);
                repository.save(js);
            }

        } catch (IOException e) {
            throw new RuntimeException("File deletion failed");
        }
    }
}