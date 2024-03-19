package com.Myrestaurant.Myrestaurant.Service;

import com.Myrestaurant.Myrestaurant.Entity.TableEntity;
import com.Myrestaurant.Myrestaurant.Repositery.TableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService   {

    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }


    public TableEntity getTableById(Long id) {
        Optional<TableEntity> optionalTable = tableRepository.findById(id);
        return optionalTable.orElse(null);
    }

 
    public TableEntity createTable(TableEntity table) {
        return tableRepository.save(table);
    }

  
    public TableEntity updateTable(Long id, TableEntity table) {
        if (tableRepository.existsById(id)) {
            table.setTblId(id); // Ensure the correct ID is set
            return tableRepository.save(table);
        } else {
            return null;
        }
    }


    public boolean deleteTable(Long id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
