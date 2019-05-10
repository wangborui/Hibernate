package contact_management;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Phone {
    @Id
    private String id;
    @ManyToMany(mappedBy = "phoneList")
    private List<Contact> contacts;
}
