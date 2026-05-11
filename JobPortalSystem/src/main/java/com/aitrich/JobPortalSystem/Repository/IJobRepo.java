package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.DTO.JobResponseDTO;
import com.aitrich.JobPortalSystem.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IJobRepo extends JpaRepository<Job,Long> {
    @Query(value = "SELECT * FROM job j " +
            "WHERE (:keyword IS NULL OR LOWER(j.job_description) LIKE LOWER(CONCAT('%', :keyword, '%')))",
            nativeQuery = true)
    List<Job> searchJobs(String keyword);

}
