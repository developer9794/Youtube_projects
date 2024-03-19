package com.Myrestaurant.Myrestaurant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerEntity{

    public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	public String getTbl() {
		return tbl;
	}

	public void setTbl(String tbl) {
		this.tbl = tbl;
	}

	public String getTblID() {
		return tblID;
	}

	public void setTblID(String tblID) {
		this.tblID = tblID;
	}

	public CustomerEntity(Long custId, String custName, String custEmail, String custContact, String tbl,
			String tblID) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custContact = custContact;
		this.tbl = tbl;
		this.tblID = tblID;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    private String custName;
    private String custEmail;
    private String custContact;
    private String  tbl;
    private String  tblID;

      public CustomerEntity() {
    }

}
