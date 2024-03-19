package com.Myrestaurant.Myrestaurant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private Long restId;
    private String menuFoodcategory;
    private String menuFood;
    private String menuFoodinfo;
    private String menuFoodtest;
    private int menuFoodqty;
    private double menuFoodprice;
    private double menuTotalAmount;
    private String menuStatus;

    // Constructors, getters, setters, and other methods

    public MenuEntity() {
    }

    public MenuEntity(Long restId, String menuFoodcategory, String menuFood, String menuFoodinfo, String menuFoodtest,
                int menuFoodqty, double menuFoodprice, double menuTotalAmount, String menuStatus) {
        this.restId = restId;
        this.menuFoodcategory = menuFoodcategory;
        this.menuFood = menuFood;
        this.menuFoodinfo = menuFoodinfo;
        this.menuFoodtest = menuFoodtest;
        this.menuFoodqty = menuFoodqty;
        this.menuFoodprice = menuFoodprice;
        this.menuTotalAmount = menuTotalAmount;
        this.menuStatus = menuStatus;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getMenuFoodcategory() {
        return menuFoodcategory;
    }

    public void setMenuFoodcategory(String menuFoodcategory) {
        this.menuFoodcategory = menuFoodcategory;
    }

    public String getMenuFood() {
        return menuFood;
    }

    public void setMenuFood(String menuFood) {
        this.menuFood = menuFood;
    }

    public String getMenuFoodinfo() {
        return menuFoodinfo;
    }

    public void setMenuFoodinfo(String menuFoodinfo) {
        this.menuFoodinfo = menuFoodinfo;
    }

    public String getMenuFoodtest() {
        return menuFoodtest;
    }

    public void setMenuFoodtest(String menuFoodtest) {
        this.menuFoodtest = menuFoodtest;
    }

    public int getMenuFoodqty() {
        return menuFoodqty;
    }

    public void setMenuFoodqty(int menuFoodqty) {
        this.menuFoodqty = menuFoodqty;
    }

    public double getMenuFoodprice() {
        return menuFoodprice;
    }

    public void setMenuFoodprice(double menuFoodprice) {
        this.menuFoodprice = menuFoodprice;
    }

    public double getMenuTotalAmount() {
        return menuTotalAmount;
    }

    public void setMenuTotalAmount(double menuTotalAmount) {
        this.menuTotalAmount = menuTotalAmount;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", restId=" + restId +
                ", menuFoodcategory='" + menuFoodcategory + '\'' +
                ", menuFood='" + menuFood + '\'' +
                ", menuFoodinfo='" + menuFoodinfo + '\'' +
                ", menuFoodtest='" + menuFoodtest + '\'' +
                ", menuFoodqty=" + menuFoodqty +
                ", menuFoodprice=" + menuFoodprice +
                ", menuTotalAmount=" + menuTotalAmount +
                ", menuStatus='" + menuStatus + '\'' +
                '}';
    }
}
