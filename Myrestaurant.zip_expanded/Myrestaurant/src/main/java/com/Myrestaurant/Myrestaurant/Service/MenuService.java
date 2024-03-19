package com.Myrestaurant.Myrestaurant.Service;

import com.Myrestaurant.Myrestaurant.Entity.MenuEntity;
import com.Myrestaurant.Myrestaurant.Repositery.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService  {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

  
    public List<MenuEntity> getAllMenus() {
        return menuRepository.findAll();
    }

  
    public MenuEntity getMenuById(Long id) {
        Optional<MenuEntity> optionalMenu = menuRepository.findById(id);
        return optionalMenu.orElse(null);
    }

  
    public MenuEntity createMenu(MenuEntity menu) {
        return menuRepository.save(menu);
    }
    public List<MenuEntity> createMenusList(List<MenuEntity> menus) {
        List<MenuEntity> createdMenus = new ArrayList<>();
        
        for (MenuEntity menu : menus) {
            MenuEntity createdMenu = createMenu(menu); // Assuming you have a createMenu method
            createdMenus.add(createdMenu);
        }
        
        return createdMenus;
    }

    public MenuEntity updateMenu(Long id, MenuEntity menu) {
        if (menuRepository.existsById(id)) {
            menu.setMenuId(id); // Ensure the correct ID is set
            return menuRepository.save(menu);
        } else {
            return null;
        }
    }

 
    public boolean deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


	public List<MenuEntity> createMenusList1(List<MenuEntity> menus) {
		// TODO Auto-generated method stub
		return null;
	}
}
