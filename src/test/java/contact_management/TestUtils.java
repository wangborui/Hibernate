package contact_management;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class TestUtils {
    public Contact getRandomContact(String id) {
        Faker faker = new Faker();
        return new Contact(id,
                faker.name().firstName(),
                faker.name().lastName(),
                new ArrayList<Phone>());
    }

    public List<Contact> getRandomContacts(List<String> ids) {
        Faker faker = new Faker();
        List<Contact> contacts = new ArrayList<Contact>();
        for (String id : ids) {
            contacts.add(new Contact(id,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    new ArrayList<Phone>()));
        }
        return contacts;
    }
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    public Phone getPhone() {
        return new Phone(getUUID(), "1234566", "N/A", PhoneType.HOME, false);
    }
}
