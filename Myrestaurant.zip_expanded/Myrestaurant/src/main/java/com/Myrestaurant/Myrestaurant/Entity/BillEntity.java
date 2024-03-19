package com.Myrestaurant.Myrestaurant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordId;

    private Long custId;
    private Long tblId;
    private Long restId;
    private LocalDateTime billDateTime;
    private String billTableNo;
    private double billTotalAmount;
    private String billPaytype;
    private String billStatus;

    // Constructors, getters, setters, and other methods

    public BillEntity() {
    }

    public BillEntity(Long custId, Long tblId, Long restId, LocalDateTime billDateTime, String billTableNo,
                double billTotalAmount, String billPaytype, String billStatus) {
        this.custId = custId;
        this.tblId = tblId;
        this.restId = restId;
        this.billDateTime = billDateTime;
        this.billTableNo = billTableNo;
        this.billTotalAmount = billTotalAmount;
        this.billPaytype = billPaytype;
        this.billStatus = billStatus;
    }

    public Long getOrdId() {
        return ordId;
    }

    public void setOrdId(Long ordId) {
        this.ordId = ordId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getTblId() {
        return tblId;
    }

    public void setTblId(Long tblId) {
        this.tblId = tblId;
    }

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public LocalDateTime getBillDateTime() {
        return billDateTime;
    }

    public void setBillDateTime(LocalDateTime billDateTime) {
        this.billDateTime = billDateTime;
    }

    public String getBillTableNo() {
        return billTableNo;
    }

    public void setBillTableNo(String billTableNo) {
        this.billTableNo = billTableNo;
    }

    public double getBillTotalAmount() {
        return billTotalAmount;
    }

    public void setBillTotalAmount(double billTotalAmount) {
        this.billTotalAmount = billTotalAmount;
    }

    public String getBillPaytype() {
        return billPaytype;
    }

    public void setBillPaytype(String billPaytype) {
        this.billPaytype = billPaytype;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "ordId=" + ordId +
                ", custId=" + custId +
                ", tblId=" + tblId +
                ", restId=" + restId +
                ", billDateTime=" + billDateTime +
                ", billTableNo='" + billTableNo + '\'' +
                ", billTotalAmount=" + billTotalAmount +
                ", billPaytype='" + billPaytype + '\'' +
                ", billStatus='" + billStatus + '\'' +
                '}';
    }
}
