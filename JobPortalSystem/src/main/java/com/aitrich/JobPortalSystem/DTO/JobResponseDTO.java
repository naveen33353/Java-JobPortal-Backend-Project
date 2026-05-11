package com.aitrich.JobPortalSystem.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobResponseDTO {


    private String description;
    private LocalDate postedDate;
    private LocalDate endDate;
    private List<String> skills;
    private String experience;
    private Double salary;
    private String companyName;

}
