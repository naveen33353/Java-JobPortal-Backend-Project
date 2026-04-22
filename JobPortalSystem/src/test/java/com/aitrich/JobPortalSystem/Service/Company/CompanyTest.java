package com.aitrich.JobPortalSystem.Service.Company;

import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Repository.ICompanyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Company Test")
public class CompanyTest {

    @Mock
    private ICompanyRepo repository;

    @InjectMocks
    private CompanyServiceImp service;

    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();

        company.setId(1L);
        company.setCompanyName("TCS");
        company.setEmail("tcs@gmail.com");
        company.setPassword("12345");
        company.setWebsite("www.tcs.com");
        company.setLocation("India");
        company.setDescription("IT Company");
    }

    @Nested
    @DisplayName("Create Company")
    class CreateCompanyTest {

        @Test
        @DisplayName("Save company successfully")
        void createCompany() {

            when(repository.save(company)).thenReturn(company);

            Company result = service.saveCompany(company);

            assertNotNull(result);
            assertEquals("TCS", result.getCompanyName());

            verify(repository).save(company);
        }
    }

    @Nested
    @DisplayName("Get All Companies")
    class GetAllCompaniesTest {

        @Test
        void getAllCompanies() {

            when(repository.findAll()).thenReturn(Arrays.asList(company));

            List<Company> result = service.getAllCompanies();

            assertNotNull(result);
            assertEquals(1, result.size());

            verify(repository).findAll();
        }
    }

    @Nested
    @DisplayName("Get Company By Id")
    class GetCompanyById {

        @Test
        void getCompanyById_success() {

            when(repository.findById(1L)).thenReturn(Optional.of(company));

            Company result = service.getCompanyById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());

            verify(repository).findById(1L);
        }

        @Test
        void getCompanyById_notFound() {
            when(repository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class,
                    () -> service.getCompanyById(1L));
        }
    }

    @Nested
    @DisplayName("Update Company")
    class UpdateCompanyTest {

        @Test
        void updateCompany() {

            Company updated = new Company();
            updated.setCompanyName("Infosys");
            updated.setEmail("info@gmail.com");

            when(repository.findById(1L)).thenReturn(Optional.of(company));
            when(repository.save(any(Company.class))).thenReturn(company);

            Company result = service.updateCompany(1L, updated);

            assertNotNull(result);
            assertEquals("Infosys", result.getCompanyName());

            verify(repository).findById(1L);
            verify(repository).save(company);
        }
    }

    @Nested
    @DisplayName("Delete Company")
    class DeleteCompanyTest {

        @Test
        void deleteCompany() {
            doNothing().when(repository).deleteById(1L);

            String result = service.deleteCompany(1L);

            assertEquals("Company deleted successfully", result);

            verify(repository).deleteById(1L);
        }
    }

}
