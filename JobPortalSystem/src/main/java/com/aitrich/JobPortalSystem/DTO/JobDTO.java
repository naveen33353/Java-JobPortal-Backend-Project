package com.aitrich.JobPortalSystem.DTO;


import com.aitrich.JobPortalSystem.Entity.Company;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobDTO {
    private Long jobId;
    private String description;
    private LocalDate postedDate;
    private LocalDate endDate;
    private List<String> skills;
    private String experience;
    private Double salary;

    private Long companyId;
    private String companyName;
}