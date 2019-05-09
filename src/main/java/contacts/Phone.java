package contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone implements java.io.Serializable {
    @Id
    @Column
    private String phoneId;
    @Column
    private String contactId;
    @Column
    private String phoneNumber;
    @Column
    private String customType;
    @Column
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @Column
    private boolean deleted;
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "phoneNumber", cascade = CascadeType.ALL  )
    private List<Contact> contacts;
}