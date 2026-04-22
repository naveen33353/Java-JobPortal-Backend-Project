package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepo extends JpaRepository<Company, Long> {

     Optional<Company> findByEmail(String email);

    List<Company> findByCompanyNameContainingIgnoreCase(String name);

    List<Company> findByLocationContainingIgnoreCase(String location);

    List<Company> findByCompanyNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name, String location);




}
