package com.myproject.organization.repository;

import com.myproject.organization.entites.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPanelRepository extends JpaRepository<AdminPanel, Long> {
    AdminPanel findByEmail(String email);
}