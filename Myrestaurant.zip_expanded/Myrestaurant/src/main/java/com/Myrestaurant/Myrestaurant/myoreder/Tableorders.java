package com.Myrestaurant.Myrestaurant.myoreder;
import javax.persistence.*;

import com.Myrestaurant.Myrestaurant.Entity.CustomerEntity;
import com.Myrestaurant.Myrestaurant.Entity.RestaurantsEntity;
import com.Myrestaurant.Myrestaurant.Entity.TableEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tableorders")
public class Tableorders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tableorder_id")
    private Long tableOrderId;
    @OneToOne
    @JoinColumn(name = "tblId")
    private TableEntity tbl;

    @OneToOne
    @JoinColumn(name = "custId")
    private CustomerEntity customer;
    @OneToOne
    @JoinColumn(name = "restId")
    private RestaurantsEntity restaurant;
    @OneToMany
    @JoinColumn(name = "myOrd_Id")
    private List<Order> myOrder;
    
    @Column(name = "ord_datetime")
    private LocalDate ordDatetime;

    @Column(name = "ord_Total")
    private double ordTotal;

    @Column(name = "ord_Status")
    private String ordStatus;

	public Long getTableOrderId() {
		return tableOrderId;
	}

	public void setTableOrderId(Long tableOrderId) {
		this.tableOrderId = tableOrderId;
	}

	public TableEntity getTbl() {
		return tbl;
	}

	public void setTbl(TableEntity tbl) {
		this.tbl = tbl;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public RestaurantsEntity getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantsEntity restaurant) {
		this.restaurant = restaurant;
	}

	public List<Order> getMyOrd_Id() {
		return myOrder;
	}

	public void setMyOrd_Id(List<Order> myOrd_Id) {
		this.myOrder = myOrd_Id;
	}

	public LocalDate getOrdDatetime() {
		return ordDatetime;
	}

	public void setOrdDatetime(LocalDate ordDatetime) {
		this.ordDatetime = ordDatetime;
	}

	public double getOrdTotal() {
		return ordTotal;
	}

	public void setOrdTotal(double ordTotal) {
		this.ordTotal = ordTotal;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Tableorders(Long tableOrderId, TableEntity tbl, CustomerEntity customer, RestaurantsEntity restaurant,
			List<Order> myOrd_Id, LocalDate ordDatetime, double ordTotal, String ordStatus) {
		super();
		this.tableOrderId = tableOrderId;
		this.tbl = tbl;
		this.customer = customer;
		this.restaurant = restaurant;
		this.myOrder = myOrd_Id;
		this.ordDatetime = ordDatetime;
		this.ordTotal = ordTotal;
		this.ordStatus = ordStatus;
	}

	public Tableorders() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}
