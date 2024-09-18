package com.myproject.organization.services;

import com.myproject.organization.entites.Organization;
import com.myproject.organization.enums.Category;
import com.myproject.organization.exceptions.OrganizationNotFoundException;
import com.myproject.organization.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization findById(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow(() -> new OrganizationNotFoundException("Organization with id " + id + " not found"));
    }

    public List<Organization> findAll() {return organizationRepository.findAll();}
    public List<Organization> getOrganizationsByCategory(Category category) {
        return organizationRepository.findByCategory(category);
    }
    @Transactional
    public void updateOrganizationPrice(Long id, Double price) {
        organizationRepository.updatePrice(id, price);
    }
}
