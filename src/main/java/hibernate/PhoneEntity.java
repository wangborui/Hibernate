package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "phone", schema = "examplebank", catalog = "")
public class PhoneEntity {
    private int id;
    private String phoneNumber;
    private Object phoneType;
    private String customType;
    private boolean isDeleted;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "phone_type", nullable = false)
    public Object getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Object phoneType) {
        this.phoneType = phoneType;
    }

    @Basic
    @Column(name = "custom_type", nullable = true, length = 50)
    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    @Basic
    @Column(name = "isDeleted", nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntity that = (PhoneEntity) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (phoneType != null ? !phoneType.equals(that.phoneType) : that.phoneType != null) return false;
        if (customType != null ? !customType.equals(that.customType) : that.customType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (phoneType != null ? phoneType.hashCode() : 0);
        result = 31 * result + (customType != null ? customType.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
