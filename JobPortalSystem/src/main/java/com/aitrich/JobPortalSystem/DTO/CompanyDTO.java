package com.aitrich.JobPortalSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String companyName;

    private String email;

    private String website;

    private String location;

    private String description;
}
