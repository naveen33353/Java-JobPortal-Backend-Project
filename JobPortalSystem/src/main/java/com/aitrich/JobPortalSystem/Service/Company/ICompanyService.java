package com.aitrich.JobPortalSystem.Service.Company;

import com.aitrich.JobPortalSystem.Entity.Company;

import java.util.List;

public interface ICompanyService {



    public Company updateCompany(Long id, Company updatedCompany);
    public String deleteCompany(Long id);

    public Company saveCompany(Company company);
    public List<Company> getAllCompanies();
    public Company getCompanyById(Long id);


}
