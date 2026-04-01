package com.aitrich.JobPortalSystem.DTO;

import com.aitrich.JobPortalSystem.Entity.Job;
import com.aitrich.JobPortalSystem.Entity.JobSeeker;
import com.aitrich.JobPortalSystem.Enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationPostDTO {

    @NotNull(message = "Job seeker should not be blank!")
    private JobSeeker jobSeeker;

    @NotNull(message = "Job should not be blank!")
    private Job job;

    private Status status;

    private LocalDate appliedDate;
}
