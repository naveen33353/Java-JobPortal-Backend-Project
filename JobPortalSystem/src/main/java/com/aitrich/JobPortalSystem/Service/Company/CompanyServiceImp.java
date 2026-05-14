package com.aitrich.JobPortalSystem.Service.Company;

import com.aitrich.JobPortalSystem.Entity.Company;
import com.aitrich.JobPortalSystem.Repository.ICompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.aitrich.JobPortalSystem.Entity.User;
import com.aitrich.JobPortalSystem.Enums.Role;
import com.aitrich.JobPortalSystem.Repository.IUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements ICompanyService {


    private final ICompanyRepo repository;
    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Company saveCompany(Company company) {

        User user = new User();

        user.setEmail(company.getEmail());
        user.setPassword(passwordEncoder.encode(company.getPassword()));
        user.setRole(Role.COMPANY);

        userRepo.save(user);

        company.setPassword(user.getPassword());

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
}
