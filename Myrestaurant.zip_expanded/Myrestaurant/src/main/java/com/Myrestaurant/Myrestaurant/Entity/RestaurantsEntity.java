package com.Myrestaurant.Myrestaurant.Entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RestaurantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restId;

    private String restUser;
    private String restPassword;
    private String restName;
    private String restInfo;
    private String restLocation;
    private String restContact;
    private String restOpen;
    private String restClose;
    private String restHoliday;

    // Constructors, getters, setters, and other methods

    public RestaurantsEntity() {
    }

    // Other constructors

    // Getters and setters

    @Override
    public String toString() {
        return "Restaurant{" +
                "restId=" + restId +
                ", restUser='" + restUser + '\'' +
                ", restPassword='" + restPassword + '\'' +
                ", restName='" + restName + '\'' +
                ", restInfo='" + restInfo + '\'' +
                ", restLocation='" + restLocation + '\'' +
                ", restContact='" + restContact + '\'' +
                ", restOpen='" + restOpen + '\'' +
                ", restClose='" + restClose + '\'' +
                ", restHoliday='" + restHoliday + '\'' +
                '}';
    }

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public String getRestUser() {
		return restUser;
	}

	public void setRestUser(String restUser) {
		this.restUser = restUser;
	}

	public String getRestPassword() {
		return restPassword;
	}

	public void setRestPassword(String restPassword) {
		this.restPassword = restPassword;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getRestInfo() {
		return restInfo;
	}

	public void setRestInfo(String restInfo) {
		this.restInfo = restInfo;
	}

	public String getRestLocation() {
		return restLocation;
	}

	public void setRestLocation(String restLocation) {
		this.restLocation = restLocation;
	}

	public String getRestContact() {
		return restContact;
	}

	public void setRestContact(String restContact) {
		this.restContact = restContact;
	}

	public String getRestOpen() {
		return restOpen;
	}

	public void setRestOpen(String restOpen) {
		this.restOpen = restOpen;
	}

	public String getRestClose() {
		return restClose;
	}

	public void setRestClose(String restClose) {
		this.restClose = restClose;
	}

	public String getRestHoliday() {
		return restHoliday;
	}

	public void setRestHoliday(String restHoliday) {
		this.restHoliday = restHoliday;
	}

	public RestaurantsEntity(Long restId, String restUser, String restPassword, String restName, String restInfo,
			String restLocation, String restContact, String restOpen, String restClose, String restHoliday) {
		super();
		this.restId = restId;
		this.restUser = restUser;
		this.restPassword = restPassword;
		this.restName = restName;
		this.restInfo = restInfo;
		this.restLocation = restLocation;
		this.restContact = restContact;
		this.restOpen = restOpen;
		this.restClose = restClose;
		this.restHoliday = restHoliday;
	}

	
}
