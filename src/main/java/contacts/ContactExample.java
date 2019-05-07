package contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactExample {
    ContactDAO contactDAO = new ContactDAO();

    public static void main(String[] args) {
        System.out.println("Hibernate Contact Example starting");
        ContactExample example = new ContactExample();
        Long contactId = example.addContact();
        System.out.println(contactId);
        example.updateContactName(contactId, "Something Else");
        example.deleteContact(contactId);
        System.out.println("Done");
    }

    private Long addContact() {
        String name = "Someone";
        List<Phone> phones = new ArrayList<Phone>();

        phones.add(new Phone("123-456", null, PhoneType.WORK, false));
        phones.add(new Phone("123-456-789", null, PhoneType.HOME, false));

        return contactDAO.addContactWithPhone(name, phones);
    }

    private void updateContactName(Long contactId, String contactName) {
        contactDAO.updateContactName(contactId, contactName);
    }

    private void deleteContact(Long contactId) {
        contactDAO.deleteContact(contactId);
    }
}
