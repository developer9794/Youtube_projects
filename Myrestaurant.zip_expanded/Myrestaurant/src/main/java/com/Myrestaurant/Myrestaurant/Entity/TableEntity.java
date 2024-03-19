package com.Myrestaurant.Myrestaurant.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TableEntity {

    public TableEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TableEntity(Long tblId, Long tblSites, Long restId, String tblName) {
		super();
		this.tblId = tblId;
		this.tblSites = tblSites;
		this.restId = restId;
		this.tblName = tblName;
	}
	public Long getTblId() {
		return tblId;
	}
	public void setTblId(Long tblId) {
		this.tblId = tblId;
	}
	public Long getTblSites() {
		return tblSites;
	}
	public void setTblSites(Long tblSites) {
		this.tblSites = tblSites;
	}
	public Long getRestId() {
		return restId;
	}
	public void setRestId(Long restId) {
		this.restId = restId;
	}
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tblId;

    private Long tblSites;
    private Long restId;
    private String tblName;
	
}
