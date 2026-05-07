package com.aitrich.JobPortalSystem.Controller;

import com.aitrich.JobPortalSystem.Service.Company.ICompanyService;
import com.aitrich.JobPortalSystem.Entity.Company;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies") 
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService service;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(service.saveCompany(company));
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(service.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCompanyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id,
                                                 @RequestBody Company company) {
        return ResponseEntity.ok(service.updateCompany(id, company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteCompany(id));
    }
}