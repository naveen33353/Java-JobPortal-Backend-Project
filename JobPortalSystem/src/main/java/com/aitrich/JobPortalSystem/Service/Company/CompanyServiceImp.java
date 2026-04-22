package com.aitrich.JobPortalSystem.Service.Company;

import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Repository.ICompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements ICompanyService {


    private final ICompanyRepo repository;

    @Override
    public Company saveCompany(Company company) {

        return repository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Company company = getCompanyById(id);

        company.setCompanyName(updatedCompany.getCompanyName());
        company.setEmail(updatedCompany.getEmail());
        company.setWebsite(updatedCompany.getWebsite());
        company.setLocation(updatedCompany.getLocation());
        company.setDescription(updatedCompany.getDescription());

        return repository.save(company);
    }

    @Override
    public String deleteCompany(Long id) {
        repository.deleteById(id);
        return "Company deleted successfully";
    }

    @Override
    public List<Job> getJobsByCompanyId(Long companyId) {
        Company company = repository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return company.getJobs(); // assuming OneToMany mapping
    }

    @Override
    public List<Company> searchCompanies(String name, String location) {

        if (name != null && location != null) {
            return repository.findByCompanyNameContainingIgnoreCaseAndLocationContainingIgnoreCase(name, location);
        } else if (name != null) {
            return repository.findByCompanyNameContainingIgnoreCase(name);
        } else if (location != null) {
            return repository.findByLocationContainingIgnoreCase(location);
        } else {
            return repository.findAll();
        }
    }
}
