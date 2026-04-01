package com.aitrich.JobPortalSystem.Service.Application;

import com.aitrich.JobPortalSystem.DTO.ApplicationPostDTO;
import com.aitrich.JobPortalSystem.Entity.Application;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Enums.Status;
import com.aitrich.JobPortalSystem.Repository.IApplicationRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
@DisplayName("Application service ")
class ApplicationServiceTest {

    @Mock
    private IApplicationRepo applicationRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ApplicationService applicationService;

    private ApplicationPostDTO dto;
    private Application application;

    @BeforeEach
    void setUp() {
        dto = new ApplicationPostDTO();
        application = new Application();

        dto.setJobSeeker(new JobSeeker());
        dto.setJob(new Job());
        dto.setStatus(Status.APPROVED);
        dto.setAppliedDate(LocalDate.now());

        application.setId(1L);
        application.setJobSeeker(new JobSeeker());
        application.setJob(new Job());
        application.setStatus(Status.APPROVED);
        application.setAppliedDate(LocalDate.now());
    }

    @Nested
    @DisplayName("Post an application")
    class PostApplicationTest{

        @Test
        @DisplayName("Should post an application")
        void postApplicationTest() {

            when(modelMapper.map(dto, Application.class)).thenReturn(application);
            when(applicationRepo.save(application)).thenReturn(application);
            when(modelMapper.map(application, ApplicationPostDTO.class)).thenReturn(dto);

            ApplicationPostDTO actualResult = applicationService.postApplication(dto);

            assertNotNull(actualResult);
            assertEquals(dto, actualResult);

            verify(modelMapper).map(dto, Application.class);
            verify(applicationRepo).save(application);
            verify(modelMapper).map(application, ApplicationPostDTO.class);
        }
    }

    @Nested
    @DisplayName("")
    class GetApplicationByIdTest{
        @Test
        @DisplayName("Should find an application")
        void getApplicationByIdTest(){
            Long id = application.getId();
            when(applicationRepo.findById(id)).thenReturn(Optional.of(application));
            Application actualResult = applicationService.getApplicationById(id);

            assertNotNull(actualResult);
            assertEquals(application, actualResult);

            verify(applicationRepo).findById(id);
        }
    }

    @Nested
    @DisplayName("Should return all applications")
    class GetAllApplicationsTest{
        @Test
        @DisplayName("Should return all applications")
        void getAllApplicationsTest(){
            when(applicationRepo.findAll()).thenReturn(List.of(application));

            List<Application> actualResult = applicationService.getAllApplications();

            assertNotNull(actualResult);
            assertEquals(1, actualResult.size());
            assertEquals(application, actualResult.get(0));
            verify(applicationRepo).findAll();
        }
    }

    @Nested
    @DisplayName("Update application")
    class UpdateApplicationTest{
        @Test
        @DisplayName("Should update an application")
        void updateApplicationTest() {
            ApplicationPostDTO applicationDTO = new ApplicationPostDTO();
            Application application = new Application();

            Long id = application.getId();

            when(applicationRepo.findById(id)).thenReturn(Optional.of(application));
            when(applicationRepo.save(application)).thenReturn(application);
            doNothing().when(modelMapper).map(applicationDTO, application);
            Application actualResult = applicationService.updateApplication(applicationDTO, id);

            assertNotNull(actualResult);
            assertEquals(application, actualResult);

            verify(applicationRepo).findById(id);
            verify(modelMapper).map(applicationDTO, application);
            verify(applicationRepo).save(application);
        }
    }

    @Nested
    @DisplayName("Delete application")
    class DeleteApplicationTest{
        @Test
        @DisplayName("Should delete an application")
        void deleteApplicationTest(){
            Long id = application.getId();
            applicationService.deleteApplicationById(id);
            verify(applicationRepo).deleteById(id);
        }
    }
}