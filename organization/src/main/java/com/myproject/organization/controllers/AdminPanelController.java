package com.myproject.organization.controllers;

import com.myproject.organization.entites.AdminPanel;
import com.myproject.organization.services.AdminPanelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminPanelController {

    private final AdminPanelService adminPanelService;

    public AdminPanelController(AdminPanelService adminPanelService) {
        this.adminPanelService = adminPanelService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminPanel adminPanel) {
        try {
            adminPanelService.save(adminPanel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Kayıt başarılı!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());  // Zaten kayıtlı mesajı
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody AdminPanel adminPanel) {
        boolean isAuthenticated = adminPanelService.authenticate(adminPanel.getEmail(), adminPanel.getPassword());
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // E-posta güncelleme endpoint'i
    @PutMapping("/update-email/{id}")
    public ResponseEntity<AdminPanel> updateEmail(@PathVariable Long id, @RequestBody String newEmail) {
        AdminPanel updatedAdmin = adminPanelService.updateEmail(id, newEmail);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    // Şifre güncelleme endpoint'i
    @PutMapping("/update-password/{id}")
    public ResponseEntity<AdminPanel> updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        AdminPanel updatedAdmin = adminPanelService.updatePassword(id, newPassword);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }
}

