package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompanyRepo extends JpaRepository<Company, Long> {

     Optional<Company> findByEmail(String email);




}
