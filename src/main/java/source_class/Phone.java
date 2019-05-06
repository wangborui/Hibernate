package source_class;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
public class Phone {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private Integer id;
    @Column(name = "contactId")
    private String contactId;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "phoneType")
    private PhoneType phoneType;
    @Column(name = "customType", nullable = true)
    private String customType;
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }


}
