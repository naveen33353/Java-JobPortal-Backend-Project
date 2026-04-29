package com.aitrich.JobPortalSystem.Service.Job;


import com.aitrich.JobPortalSystem.DTO.JobDTO;
import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Repository.IJobRepo;

import com.aitrich.JobPortalSystem.Repository.IJobSeekerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class JobServiceTest {


    @Mock
    private IJobRepo jobRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private JobServiceImpl jobSerivce;

    @Mock
    private IJobSeekerRepo jobSeekerRepo;

    private JobDTO dto;
    private Job job;

    private Job job1;
    private Job job2;

    private JobSeeker jobSeeker;



    @BeforeEach
    void setUp() {
        dto = new JobDTO();
        job = new Job();

        job.setJobId(1L);
        job.setDescription("AI Engineer");
        job.setCompany(new Company());

        dto.setCompany(new Company());
        dto.setDescription("Software Developer");
        dto.setPostedDate(LocalDate.now());
        dto.setEndDate(LocalDate.now());

        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Spring");
        list.add("SpringBoot");
        dto.setSkills(list);
        dto.setExperience("1 year ");
        dto.setSalary(40000);

        job1.setCompany(new Company());
        job1.setDescription("Software Developer");
        job1.setJobId(100L);

        job2.setCompany(new Company());
        job2.setDescription("Java Developer");
        job2.setJobId(101L);

        jobSeeker = new JobSeeker();
        jobSeeker.setId(1L);
        jobSeeker.setSavedJobs(new ArrayList<>());



    }

    @Nested
    @DisplayName("Post a Job")
    class CreateJobTest{

        @Test
        @DisplayName("Should add a job")
        void createJob() {

            when(modelMapper.map(dto, Job.class)).thenReturn(job);
            when(jobRepo.save(job)).thenReturn(job);
            when(modelMapper.map(job, JobDTO.class)).thenReturn(dto);

            JobDTO actualResult = jobSerivce.createJob(dto);

            assertNotNull(actualResult);
            assertEquals(dto, actualResult);

            verify(modelMapper).map(dto, Job.class);
            verify(jobRepo).save(job);
            verify(modelMapper).map(job, JobDTO.class);
        }
    }

    @Nested
    @DisplayName("")
    class GetJobById{
        @Test
        @DisplayName("Should find a job")
        void getJobByIdTest(){
            Long id = job.getJobId();
            when(jobRepo.findById(id)).thenReturn(Optional.of(job));
            Job actualResult = jobSerivce.getJobById(id);

            assertNotNull(actualResult);
            assertEquals(job, actualResult);

            verify(jobRepo).findById(id);
        }
    }

    @Nested
    @DisplayName("Should return all jobs")
    class GetAllJobTest{
        @Test
        @DisplayName("Should return all applications")
        void getAllJobsTest(){
            when(jobRepo.findAll()).thenReturn(List.of(job));

            List<Job> actualResult = jobSerivce.listAllJob();

            assertNotNull(actualResult);
            assertEquals(1, actualResult.size());
            assertEquals(job, actualResult.get(0));
            verify(jobRepo).findAll();
        }
    }

    @Nested
    @DisplayName("Update job")
    class UpdateJobTest{
        @Test
        @DisplayName("Should update a job")
        void updateJobTest(){
            JobDTO jobDto = new JobDTO();
            when(jobRepo.save(job)).thenReturn(job);
            Job actualResult = jobSerivce.updateJob(job.getJobId(),jobDto);
            assertNotNull(actualResult);
            assertEquals(job, actualResult);

            verify(jobRepo).save(job);
        }
    }

    @Nested
    @DisplayName("Delete job")
    class DeleteJobTest{
        @Test
        @DisplayName("Should delete a job")
        void deleteJobTest(){
            Long id = job.getJobId();
            jobSerivce.deleteJobById(id);
            verify(jobRepo).deleteById(id);
        }
    }

    @Nested
    @DisplayName("search job")
    class SearchJob{
        @Test
        @DisplayName("Should search a job")
        void searchJobs(){
            String keyword = "developer";
            List<Job> expectedJobs = Arrays.asList(job1,job2);

            when(jobRepo.searchJobs(keyword)).thenReturn(expectedJobs);

            List<Job> result = jobSerivce.searchJob(keyword);

            assertNotNull(result);
            assertEquals(2,result.size());
            assertEquals(expectedJobs,result);

            verify(jobRepo, times(1)).searchJobs(keyword);
        }

        @Test
        @DisplayName("should return empty list where job not found")
        void shouldReturnEmptyList_whenJobNotfound(){
            String keyword = "python";

            List<Job> expectedJobs = Arrays.asList(job1,job2);

            when(jobRepo.searchJobs(keyword)).thenReturn(Collections.emptyList());

            List<Job> result = jobSerivce.searchJob(keyword);

            assertNotNull(result);
            assertTrue(result.isEmpty());

            verify(jobRepo).searchJobs(keyword);


        }
    }

    @Nested
    @DisplayName("saved jobs")
    class SavedJob{
        @Test
        @DisplayName("should mark a job as saved")
        void savedJobs(){

            when(jobSeekerRepo.findById(1L)).thenReturn(Optional.of(jobSeeker));
            when(jobRepo.findById(100L)).thenReturn(Optional.of(job1));

            jobSerivce.savedJobs(100L,1L);

            assertTrue(jobSeeker.getSavedJobs().contains(job1));
            assertEquals(1,jobSeeker.getSavedJobs().size());
        }

        @Test
        @DisplayName("should not save duplicate job")
        void shouldnotSaveduplicaeJob(){
            jobSeeker.getSavedJobs().add(job1);

            when(jobSeekerRepo.findById(1L)).thenReturn(Optional.of(jobSeeker));
            when(jobRepo.findById(100L)).thenReturn(Optional.of(job1));

            jobSerivce.savedJobs(100L,1L);

            assertEquals(1,jobSeeker.getSavedJobs().size());
        }
    }

    @Nested
    @DisplayName("remove saved jobs")
    class removeSavedJob{

        @Test
        @DisplayName("should remove job from saved list")
        void removeJobFromSavedList(){

                when(jobSeekerRepo.findById(jobSeeker.getId()))
                        .thenReturn(Optional.of(jobSeeker));
                when(jobRepo.findById(job.getJobId()))
                        .thenReturn(Optional.of(job));

                jobSerivce.removeSavedJob(job.getJobId(), jobSeeker.getId());

                assertFalse(jobSeeker.getSavedJobs().contains(job));

        }

        @Test
        @DisplayName("job not in saved list")
        void removeSavedJob_JobNotInSavedList(){
            when(jobSeekerRepo.findById(jobSeeker.getId()))
                    .thenReturn(Optional.of(jobSeeker));

            when(jobRepo.findById(job.getJobId()))
                    .thenReturn(Optional.of(job));

            jobSerivce.removeSavedJob(job.getJobId(),jobSeeker.getId());

            assertTrue(jobSeeker.getSavedJobs().isEmpty());
        }
    }

    @Nested
    @DisplayName("get all saved Jobs")

    class getSavedJobs{
        @Test
        void getAllSavedJob(){

            List<Job> savedJobs = new ArrayList<>(List.of(job1,job2));

            when(jobSeekerRepo.findById(jobSeeker.getId()))
                    .thenReturn(Optional.of(jobSeeker));

            List<Job> result = jobSerivce.getSavedJob(jobSeeker.getId());

            assertTrue(result.contains(job1));
            assertTrue(result.contains(job2));
            assertEquals(2,result.size());
        }

        @Test
        void getAllSavedJobs_jobNotInSavedList(){

            when(jobSeekerRepo.findById(jobSeeker.getId()))
                    .thenReturn(Optional.of(jobSeeker));

            List<Job> result = jobSerivce.getSavedJob(jobSeeker.getId());

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }
}
