package com.aitrich.JobPortalSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobSeekerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> skills;
    private String location;
}
