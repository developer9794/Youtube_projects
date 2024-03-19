package com.Myrestaurant.Myrestaurant.Controller;

import com.Myrestaurant.Myrestaurant.Entity.TableEntity;
import com.Myrestaurant.Myrestaurant.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<List<TableEntity>> getAllTables() {
        List<TableEntity> tables = tableService.getAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableEntity> getTableById(@PathVariable Long id) {
        TableEntity table = tableService.getTableById(id);
        if (table != null) {
            return new ResponseEntity<>(table, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TableEntity> createTable(@RequestBody TableEntity table) {
        TableEntity createdTable = tableService.createTable(table);
        return new ResponseEntity<>(createdTable, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableEntity> updateTable(@PathVariable Long id, @RequestBody TableEntity table) {
        TableEntity updatedTable = tableService.updateTable(id, table);
        if (updatedTable != null) {
            return new ResponseEntity<>(updatedTable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        boolean deleted = tableService.deleteTable(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
