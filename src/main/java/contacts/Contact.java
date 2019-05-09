package contacts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements java.io.Serializable {

    @Id
    @Column
    private String contactId;
    @Column
    private String name;
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL  )
    @JoinTable(
            name = "phone",
            joinColumns = {@JoinColumn(name = "contactId")},
            inverseJoinColumns = {@JoinColumn(name = "phoneId")}
    )
    private List<Phone> phoneNumber;
}