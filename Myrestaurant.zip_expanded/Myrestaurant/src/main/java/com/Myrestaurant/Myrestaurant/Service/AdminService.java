package com.Myrestaurant.Myrestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Myrestaurant.Myrestaurant.Entity.AdminEntity;
import com.Myrestaurant.Myrestaurant.Repositery.AdminRepository;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminEntity getAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

    public AdminEntity createAdmin(AdminEntity admin) {
        return adminRepository.save(admin);
    }

    public AdminEntity updateAdmin(Long adminId, AdminEntity admin) {
        if (adminRepository.existsById(adminId)) {
          //  admin.setAdminEmail(adminId);
            return adminRepository.save(admin);
        }
        return null;
    }

    public boolean deleteAdmin(Long adminId) {
        if (adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
            return true;
        }
        return false;
    }
}
