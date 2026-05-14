package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.DTO.CompanyDTO;
import com.aitrich.JobPortalSystem.Service.Company.ICompanyService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aitrich.JobPortalSystem.Entity.Company;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {


    private  final ICompanyService service;
    private final ModelMapper modelMapper;

//        @PreAuthorize("hasRole('COMPANY')")
        @PostMapping
        public Company createCompany (@RequestBody Company company){

            return service.saveCompany(company);
        }

        @GetMapping
        public List<Company> getAllCompanies () {
                return service.getAllCompanies();
            }

    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable Long id){
        Company company = service.getCompanyById(id);
        return modelMapper.map(company, CompanyDTO.class);
    }

                @PreAuthorize("hasRole('COMPANY')")
                @PutMapping("/{id}")
                public Company updateCompany (@PathVariable Long id,
                        @RequestBody Company company){
                    return service.updateCompany(id, company);
                }

                @PreAuthorize("hasRole('COMPANY')")
                @DeleteMapping("/{id}")
                public String deleteCompany (@PathVariable Long id){
                    return service.deleteCompany(id);
                }
            }



