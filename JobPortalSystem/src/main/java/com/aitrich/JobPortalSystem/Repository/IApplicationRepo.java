package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.Entity.Application;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IApplicationRepo extends JpaRepository<Application , Long> {

    public List<Application> findByJob_JobId(Long jobId);

    public List<Application> findByJobSeeker_Id(Long id);

    @Query(value = "SELECT * FROM APPLICATION WHERE LOWER(status) = 'Approved'",nativeQuery = true)
    public List<Application> findByStatus();
}