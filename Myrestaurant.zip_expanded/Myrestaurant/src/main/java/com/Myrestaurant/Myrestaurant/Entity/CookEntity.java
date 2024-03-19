package com.Myrestaurant.Myrestaurant.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookId;

    private String cookName;
    private String cookEmail;
    private String cookContact;
    private String cookPassword;
    private LocalDate cookRdate;
    private String cookStatus;

    private Long restId;

    // Constructors, getters, setters, and other methods

    public CookEntity() {
    }

    public CookEntity(String cookName, String cookEmail, String cookContact, String cookPassword, LocalDate cookRdate, String cookStatus, Long restId) {
        this.cookName = cookName;
        this.cookEmail = cookEmail;
        this.cookContact = cookContact;
        this.cookPassword = cookPassword;
        this.cookRdate = cookRdate;
        this.cookStatus = cookStatus;
        this.restId = restId;
    }

	public Long getCookId() {
		return cookId;
	}

	public void setCookId(Long cookId) {
		this.cookId = cookId;
	}

	public String getCookName() {
		return cookName;
	}

	public void setCookName(String cookName) {
		this.cookName = cookName;
	}

	public String getCookEmail() {
		return cookEmail;
	}

	public void setCookEmail(String cookEmail) {
		this.cookEmail = cookEmail;
	}

	public String getCookContact() {
		return cookContact;
	}

	public void setCookContact(String cookContact) {
		this.cookContact = cookContact;
	}

	public String getCookPassword() {
		return cookPassword;
	}

	public void setCookPassword(String cookPassword) {
		this.cookPassword = cookPassword;
	}

	public LocalDate getCookRdate() {
		return cookRdate;
	}

	public void setCookRdate(LocalDate cookRdate) {
		this.cookRdate = cookRdate;
	}

	public String getCookStatus() {
		return cookStatus;
	}

	public void setCookStatus(String cookStatus) {
		this.cookStatus = cookStatus;
	}

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

  
    }
