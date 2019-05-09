package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactExample {
    ContactDAO contactDAO = new ContactDAO();

    public static void main(String[] args) {
        System.out.println("Hibernate Contact Example starting");
        ContactExample example = new ContactExample();

        //Add contact with name
//        String contactId = example.addContactWithName("Yue");
//        System.out.println(contactId);

        //Add Contact with phone Number
        String contactId = example.addContact();
        System.out.println(contactId);

//        //Update
//        example.updateContactName(contactId, "Something Else");
//
//        //Delete
//        example.deleteContact(contactId);
    }

    private String addContact() {
        String name = "Someone";
        List<Phone> phones = new ArrayList<Phone>();

        phones.add(new Phone(UUID.randomUUID().toString(), "", "123-456", "", PhoneType.WORK, false, null));
        phones.add(new Phone(UUID.randomUUID().toString(), "", "123-456-789", "Lover", PhoneType.WORK, false, null));

        return contactDAO.addContactWithPhone(name, phones);
    }

    private String addContactWithName(String name) {
        return contactDAO.addContactWithName(name);
    }

    private void updateContactName(Long contactId, String contactName) {
        contactDAO.updateContactName(contactId, contactName);
    }

    private void deleteContact(Long contactId) {
        contactDAO.deleteContact(contactId);
    }
}
