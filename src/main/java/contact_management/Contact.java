package contact_management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Contact {
    @Id
    @Column
    private String id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany
    @JoinTable(name = "Contact_Phone")
    private List<Phone> phoneList;
}
