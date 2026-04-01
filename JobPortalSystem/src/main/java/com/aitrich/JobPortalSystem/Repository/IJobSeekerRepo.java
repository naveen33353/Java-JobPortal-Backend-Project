package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobSeekerRepo extends JpaRepository<JobSeeker, Long> {


    JobSeeker findByEmail(String email);

}
