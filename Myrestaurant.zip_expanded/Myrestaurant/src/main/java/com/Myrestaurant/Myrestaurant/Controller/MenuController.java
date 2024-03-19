package com.Myrestaurant.Myrestaurant.Controller;

import com.Myrestaurant.Myrestaurant.Entity.MenuEntity;
import com.Myrestaurant.Myrestaurant.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuEntity>> getAllMenus() {
        List<MenuEntity> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }


    @GetMapping("/{restid}")
    public ResponseEntity<List<MenuEntity>> getMenuById(@PathVariable Long restid) {
        // Retrieve all menus
        List<MenuEntity> allMenus = menuService.getAllMenus();

        // Filter menus by restaurant ID
        List<MenuEntity> filteredMenus = allMenus.stream()
                .filter(menu -> menu.getRestId().equals(restid))
                .collect(Collectors.toList());

        if (filteredMenus.isEmpty()) {
            // If no menus were found for the specified restaurant, return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Return the filtered menus with a 200 OK status
            return new ResponseEntity<>(filteredMenus, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<MenuEntity> createMenu(@RequestBody MenuEntity menu) {
        MenuEntity createdMenu = menuService.createMenu(menu);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }
    @PostMapping("/list")
    public ResponseEntity<List<MenuEntity>> createMenus(@RequestBody List<MenuEntity> menus) {
    	
List<MenuEntity> createdMenus = new ArrayList<>();
        
        for (MenuEntity menu : menus) {
           
            MenuEntity createdMenus1= menuService.createMenu(menu);
        }
        
       // Modify your service method accordingly
        return new ResponseEntity<>(createdMenus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuEntity> updateMenu(@PathVariable Long id, @RequestBody MenuEntity menu) {
        MenuEntity updatedMenu = menuService.updateMenu(id, menu);
        if (updatedMenu != null) {
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        boolean deleted = menuService.deleteMenu(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
