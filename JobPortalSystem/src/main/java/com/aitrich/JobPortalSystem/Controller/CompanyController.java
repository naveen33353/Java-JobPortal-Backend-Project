package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Service.Company.ICompanyService;
import lombok.RequiredArgsConstructor;

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

        @PostMapping("/Company")
        public Company createCompany (@RequestBody Company company){

            return service.saveCompany(company);
        }

        @GetMapping("/")
        public List<Company> getAllCompanies () {

            return service.getAllCompanies();
            }

            @GetMapping("/{id}")
            public Company getCompanyById (@PathVariable Long id){

                    return service.getCompanyById(id);
                }

                @PutMapping("/{id}")
                public Company updateCompany (@PathVariable Long id,
                        @RequestBody Company company){
                    return service.updateCompany(id, company);
                }

                @DeleteMapping("/{id}")
                public String deleteCompany (@PathVariable Long id){

                return service.deleteCompany(id);
                }

                @GetMapping("/{id}/jobs")
                public List<Job> getCompanyJobs(@PathVariable Long id){
                    return service.getJobsByCompanyId(id);
                }

                @GetMapping("/search")
                public List<Company> searchCompanies(
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) String location) {
                    return service.searchCompanies(name, location);
                }
        }





