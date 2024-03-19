//package com.Myrestaurant.Myrestaurant.myoreder;
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "Tableallorder")
//public class Tableallorder {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "tableallorder_id")
//    private Long tableAllOrderId;
//
//    @Column(name = "ordall_datetime")
//    private Date ordAllDatetime;
//
//    @Column(name = "ordall_Total")
//    private double ordAllTotal;
//
//    @Column(name = "ordall_Status")
//    private String ordAllStatus;
//
//    // Constructors
//    public Tableallorder() {
//    }
//
//    public Tableallorder(Date ordAllDatetime, double ordAllTotal, String ordAllStatus) {
//        this.ordAllDatetime = ordAllDatetime;
//        this.ordAllTotal = ordAllTotal;
//        this.ordAllStatus = ordAllStatus;
//    }
//
//    // Getters and setters
//    public Long getTableAllOrderId() {
//        return tableAllOrderId;
//    }
//
//    public void setTableAllOrderId(Long tableAllOrderId) {
//        this.tableAllOrderId = tableAllOrderId;
//    }
//
//    public Date getOrdAllDatetime() {
//        return ordAllDatetime;
//    }
//
//    public void setOrdAllDatetime(Date ordAllDatetime) {
//        this.ordAllDatetime = ordAllDatetime;
//    }
//
//    public double getOrdAllTotal() {
//        return ordAllTotal;
//    }
//
//    public void setOrdAllTotal(double ordAllTotal) {
//        this.ordAllTotal = ordAllTotal;
//    }
//
//    public String getOrdAllStatus() {
//        return ordAllStatus;
//    }
//
//    public void setOrdAllStatus(String ordAllStatus) {
//        this.ordAllStatus = ordAllStatus;
//    }
//}
