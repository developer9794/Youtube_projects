package com.Myrestaurant.Myrestaurant.myoreder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Myrestaurant.Myrestaurant.myoreder.Tableorders;
import com.google.zxing.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/tableorders")
public class TableordersController {

    private final TableordersService tableordersService;

    @Autowired
    public TableordersController(TableordersService tableordersService) {
        this.tableordersService = tableordersService;
    }

    @GetMapping("/get")
    public List<Tableorders> getAllTableorders() {
    	System.out.println("--------------------------------------------------");
        return tableordersService.getAllTableorders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tableorders> getTableorderById(@PathVariable Long id) throws NotFoundException {
        Tableorders tableorder = tableordersService.getTableorderById(id);
		return new ResponseEntity<>(tableorder, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Tableorders> createTableorder(@RequestBody Tableorders tableorder) {
        Tableorders createdTableorder = tableordersService.createTableorder(tableorder);
        return new ResponseEntity<>(createdTableorder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tableorders> updateTableorder(@PathVariable Long id, @RequestBody Tableorders tableorder) throws NotFoundException {
        Tableorders updatedTableorder = tableordersService.updateTableorder(id, tableorder);
		return new ResponseEntity<>(updatedTableorder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableorder(@PathVariable Long id) throws NotFoundException {
        tableordersService.deleteTableorder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
