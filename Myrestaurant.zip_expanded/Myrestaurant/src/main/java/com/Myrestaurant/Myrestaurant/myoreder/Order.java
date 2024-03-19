package com.Myrestaurant.Myrestaurant.myoreder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.Myrestaurant.Myrestaurant.Entity.CustomerEntity;
import com.Myrestaurant.Myrestaurant.Entity.MenuEntity;
import com.Myrestaurant.Myrestaurant.Entity.OrderEntity;
import com.Myrestaurant.Myrestaurant.Entity.RestaurantsEntity;
import com.Myrestaurant.Myrestaurant.Entity.TableEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "my_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myOrd_Id")
    private Long myOrd_Id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "tblId")
    private TableEntity table;

    @OneToOne
    @JoinColumn(name = "custId")
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "restId")
    private RestaurantsEntity restaurant;

    private double totalprice;

    private LocalDate datetime;

    public Long getMyOrd_Id() {
        return myOrd_Id;
    }

    public void setMyOrd_Id(Long myOrd_Id) {
        this.myOrd_Id = myOrd_Id;
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
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

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public Order() {
        super();
    }

    public Order(Long myOrd_Id, TableEntity table, CustomerEntity customer, RestaurantsEntity restaurant,
                 double totalprice, LocalDate datetime) {
        this.myOrd_Id = myOrd_Id;
        this.table = table;
        this.customer = customer;
        this.restaurant = restaurant;
        this.totalprice = totalprice;
        this.datetime = datetime;
    }
}