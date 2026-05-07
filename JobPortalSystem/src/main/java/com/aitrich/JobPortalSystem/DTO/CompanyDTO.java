package com.aitrich.JobPortalSystem.DTO;

import java.util.List;

public class CompanyDTO {

    private Long id;
    private String CompanyName;
    private String email;
    private String website;
    private String location;
    private String description;

    private List<JobDTO> jobs;

}
