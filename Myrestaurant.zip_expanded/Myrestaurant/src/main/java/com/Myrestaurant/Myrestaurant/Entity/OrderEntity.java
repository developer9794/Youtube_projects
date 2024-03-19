package com.Myrestaurant.Myrestaurant.Entity;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.Myrestaurant.Myrestaurant.myoreder.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordId;

    private String menuFoodcategory;
    private String menuFood;
    private String menuFoodinfo;
    private String menuFoodtest;
	 private String menuStatus;
    private int menuFoodqty;
    private double menuFoodprice;
	  private double totalPrice;


    @ManyToOne
    @JoinColumn(name = "myOrd_Id")
    @JsonBackReference
    private Order order;

    public OrderEntity() {
    }

	public Long getOrdId() {
		return ordId;
	}

	public void setOrdId(Long ordId) {
		this.ordId = ordId;
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

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderEntity(Long ordId, String menuFoodcategory, String menuFood, String menuFoodinfo, String menuFoodtest,
			String menuStatus, int menuFoodqty, double menuFoodprice, double totalPrice, Order order) {
		super();
		this.ordId = ordId;
		this.menuFoodcategory = menuFoodcategory;
		this.menuFood = menuFood;
		this.menuFoodinfo = menuFoodinfo;
		this.menuFoodtest = menuFoodtest;
		this.menuStatus = menuStatus;
		this.menuFoodqty = menuFoodqty;
		this.menuFoodprice = menuFoodprice;
		this.totalPrice = totalPrice;
		this.order = order;
	}

   
}
