package com.Myrestaurant.Myrestaurant.Service;

import com.Myrestaurant.Myrestaurant.Entity.CookEntity;
import com.Myrestaurant.Myrestaurant.Repositery.CookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CookService  {

    private final CookRepository cookRepository;

    @Autowired
    public CookService(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    public List<CookEntity> getAllCooks() {
        return cookRepository.findAll();
    }

    public CookEntity getCookById(Long id) {
        Optional<CookEntity> optionalCook = cookRepository.findById(id);
        return optionalCook.orElse(null);
    }

    public CookEntity createCook(CookEntity cook) {
        return cookRepository.save(cook);
    }

    public CookEntity updateCook(Long id, CookEntity cook) {
        if (cookRepository.existsById(id)) {
            cook.setCookId(id); // Ensure the correct ID is set
            return cookRepository.save(cook);
        } else {
            return null;
        }
    }

    public boolean deleteCook(Long id) {
        if (cookRepository.existsById(id)) {
            cookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
