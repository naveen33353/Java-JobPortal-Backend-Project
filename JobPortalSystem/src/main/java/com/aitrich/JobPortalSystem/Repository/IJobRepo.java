package com.aitrich.JobPortalSystem.Repository;

import com.aitrich.JobPortalSystem.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobRepo extends JpaRepository<Job,Long> {
}
