package com.Myrestaurant.Myrestaurant.myoreder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Myrestaurant.Myrestaurant.myoreder.Tableorders;

import java.util.List;
import java.util.Optional;

@Service
public class TableordersService {

    private final TableordersRepository tableordersRepository;

    @Autowired
    public TableordersService(TableordersRepository tableordersRepository) {
        this.tableordersRepository = tableordersRepository;
    }

    public List<Tableorders> getAllTableorders() {
        return tableordersRepository.findAll();
    }

    public Tableorders getTableorderById(Long id) {
        Optional<Tableorders> tableorder = tableordersRepository.findById(id);
        if (tableorder.isPresent()) {
            return tableorder.get();
        } else {
            // Handle not found scenario
        	 return tableorder.get();
//            throw new NotFoundException("Tableorder not found with id: " + id);
        }
    }

    public Tableorders createTableorder(Tableorders tableorder) {
        // You can add validation or business logic here if needed
        return tableordersRepository.save(tableorder);
    }

    public Tableorders updateTableorder(Long id, Tableorders updatedTableorder) {
        Tableorders existingTableorder = getTableorderById(id);
        // Update fields of the existingTableorder with values from updatedTableorder
        // You can implement this logic based on your requirements
        return tableordersRepository.save(existingTableorder);
    }

    public void deleteTableorder(Long id) {
        tableordersRepository.deleteById(id);
    }
}
