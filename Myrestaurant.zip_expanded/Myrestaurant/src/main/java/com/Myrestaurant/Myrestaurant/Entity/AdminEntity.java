package com.Myrestaurant.Myrestaurant.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String adminName;
    private String adminEmail;
    private String adminContact;
    private String adminPassword;
    private LocalDate adminRdate;

    private Long restId;

    // Constructors, getters, setters, and other methods

    public AdminEntity() {
    }

    public AdminEntity(String adminName, String adminEmail, String adminContact, String adminPassword, LocalDate adminRdate, Long restId) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminContact = adminContact;
        this.adminPassword = adminPassword;
        this.adminRdate = adminRdate;
        this.restId = restId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public LocalDate getAdminRdate() {
        return adminRdate;
    }

    public void setAdminRdate(LocalDate adminRdate) {
        this.adminRdate = adminRdate;
    }

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminContact='" + adminContact + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminRdate=" + adminRdate +
                ", restId=" + restId +
                '}';
    }
}
