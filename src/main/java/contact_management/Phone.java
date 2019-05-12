package contact_management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private String id;
    @ManyToMany(mappedBy = "phoneList")
    private List<Contact> contacts;
}
