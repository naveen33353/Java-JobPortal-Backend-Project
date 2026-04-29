package com.aitrich.JobPortalSystem.Entity;

import com.aitrich.JobPortalSystem.DTO.JobDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "job_seekers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name="saved_jobs",
               joinColumns = @JoinColumn(name = "job_seeker_id"),
                inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> savedJobs;

    private String resumeUrl;

    private String photoUrl;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    private String location;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
}


