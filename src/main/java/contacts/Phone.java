package contacts;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TStudent generated by hbm2java
 */
@Entity
@Table(name="phone")
public class Phone implements java.io.Serializable {

    private Long phoneId;
    private Long contactId;
    private String phoneNumber;
    private String customType;

    private PhoneType phoneType;
    private boolean isDeleted;
    private List<Contact> contacts;

    public Phone() {
    }

    public Phone(String phoneNumber, String customType, PhoneType phoneType, boolean isDeleted) {
        this.phoneNumber = phoneNumber;
        this.customType = customType;
        this.phoneType = phoneType;
        this.isDeleted = isDeleted;
    }

    public Phone(Long contactId, String phoneNumber, String customType, PhoneType phoneType, boolean isDeleted) {
        this.phoneNumber = phoneNumber;
        this.customType = customType;
        this.contactId = contactId;
        this.phoneType = phoneType;
        this.isDeleted = isDeleted;
    }

    public Phone(Long contactId, String phoneNumber, String customType, PhoneType phoneType, List<Contact> contacts) {
        this.phoneNumber = phoneNumber;
        this.customType = customType;
        this.contactId = contactId;
        this.phoneType = phoneType;
        this.contacts = contacts;
    }

    @Id @GeneratedValue(strategy=IDENTITY)

    @Column(name="phoneId", unique=true, nullable=false)
    public Long getPhoneId() {
        return this.phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    @Column(name="phoneNumber", nullable=false, length=20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="customType", nullable=true, length=20)
    public String getCustomType() {
        return this.customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    @Column(name = "phoneType", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    public PhoneType getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    @Column(name = "contactId", nullable = false, length = 20)
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
    @Column(name = "isDeleted", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "phoneNumber", cascade = CascadeType.ALL  )
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}