package com.myproject.organization.controllers;

import com.myproject.organization.entites.Organization;
import com.myproject.organization.enums.Category;
import com.myproject.organization.services.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization savedOrganization = organizationService.save(organization);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Organization>> getOrganizationsByCategory(@PathVariable Category category) {
        List<Organization> organizations = organizationService.getOrganizationsByCategory(category);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }
    @PutMapping("/{id}/price")
    public ResponseEntity<String> updatePrice(@PathVariable Long id, @RequestParam Double price) {
        organizationService.updateOrganizationPrice(id, price);
        return ResponseEntity.ok("Price updated successfully");
    }
}