package com.Myrestaurant.Myrestaurant.Controller;

import com.Myrestaurant.Myrestaurant.Entity.CookEntity;
import com.Myrestaurant.Myrestaurant.Service.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cooks")
@CrossOrigin
public class CookController {

    private final CookService cookService;

    @Autowired
    public CookController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping
    public ResponseEntity<List<CookEntity>> getAllCooks() {
        List<CookEntity> cooks = cookService.getAllCooks();
        return new ResponseEntity<>(cooks, HttpStatus.OK);
    }

    @GetMapping("/{restid}")
    public ResponseEntity<List<CookEntity>> getCooksByRestId(@PathVariable Long restid) {
        // Retrieve all cooks
        List<CookEntity> allCooks = cookService.getAllCooks();

        // Filter cooks by restaurant ID (restid)
        List<CookEntity> filteredCooks = allCooks.stream()
                .filter(cook -> cook.getRestId().equals(restid))
                .collect(Collectors.toList());

        if (filteredCooks.isEmpty()) {
            // If no cooks were found for the specified restaurant, return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Return the filtered cooks with a 200 OK status
            return new ResponseEntity<>(filteredCooks, HttpStatus.OK);
        }
    }
    @GetMapping("/byid/{id}")
    public ResponseEntity<CookEntity> getCookById(@PathVariable Long id) {
        CookEntity cook = cookService.getCookById(id);
        if (cook != null) {
            return new ResponseEntity<>(cook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CookEntity> createCook(@RequestBody CookEntity cook) {
        CookEntity createdCook = cookService.createCook(cook);
        return new ResponseEntity<>(createdCook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CookEntity> updateCook(@PathVariable Long id, @RequestBody CookEntity cook) {
        CookEntity updatedCook = cookService.updateCook(id, cook);
        if (updatedCook != null) {
            return new ResponseEntity<>(updatedCook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCook(@PathVariable Long id) {
        boolean deleted = cookService.deleteCook(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
