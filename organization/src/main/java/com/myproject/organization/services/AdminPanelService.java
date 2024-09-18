package com.myproject.organization.services;

import com.myproject.organization.entites.AdminPanel;
import com.myproject.organization.repository.AdminPanelRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminPanelService {

    private final AdminPanelRepository adminPanelRepository;

    public AdminPanelService(AdminPanelRepository adminPanelRepository) {
        this.adminPanelRepository = adminPanelRepository;
    }

    public AdminPanel save(AdminPanel adminPanel) {
        // E-posta kontrolü: Aynı e-posta ile kayıt varsa hata döndür
        if (adminPanelRepository.findByEmail(adminPanel.getEmail()) != null) {
            throw new IllegalStateException("Zaten bu e-posta adresi ile kayıtlı bir hesap var.");
        }
        // Şifreyi hashlemek gibi işlemler yapılabilir
        return adminPanelRepository.save(adminPanel);
    }

    public AdminPanel findById(Long id) {
        Optional<AdminPanel> admin = adminPanelRepository.findById(id);
        return admin.orElseThrow(() -> new RuntimeException("Admin not found!"));
    }

    public boolean authenticate(String email, String password) {
        AdminPanel adminPanel = adminPanelRepository.findByEmail(email);
        if (adminPanel != null && adminPanel.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // E-posta güncelleme fonksiyonu
    public AdminPanel updateEmail(Long id, String newEmail) {
        AdminPanel adminPanel = findById(id);
        adminPanel.setEmail(newEmail);
        return adminPanelRepository.save(adminPanel);
    }

    // Şifre güncelleme fonksiyonu
    public AdminPanel updatePassword(Long id, String newPassword) {
        AdminPanel adminPanel = findById(id);
        adminPanel.setPassword(newPassword);  // Şifreyi hashlemek iyi bir güvenlik önlemidir.
        return adminPanelRepository.save(adminPanel);
    }
}