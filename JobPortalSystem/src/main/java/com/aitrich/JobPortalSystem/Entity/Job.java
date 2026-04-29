package com.aitrich.JobPortalSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "job_title")
    private String title;

    @Column(name = "Job_Description")
    private String description;

    @OneToMany(mappedBy = "job" , cascade = CascadeType.ALL)
    private List<Application> applications;


    private LocalDate postedDate;

    private LocalDate endDate;

    @ElementCollection
    private List<String> skills;

    private String Experience;

    private double salary;

}
