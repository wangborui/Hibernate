# Hibernate
## Relationship UML
![alt text](https://github.com/wangborui/Hibernate/blob/master/Relationships.png)

## POJO Objects
* **Contact**
  * Id(NotNull)
  * First Name(NotNull)
  * Last Name(NotNull)
* **Phone**
  * Id(NotNull)
  * Phone Number(NotNull)
  * Phone Number Type(NotNull)
  * Custom Number Type
  * Deleted(is the phone number deleted?)(NotNull)

## Data Access Objects
* **ContactDao**
  * `String` Save(Contact)
    * This method takes a Contact object, saves to backend database and returns the id for the saved Contact object. Id is generated using UUID
  * `List<String>` Save(List<Contact>)
    * This method takes a list of Contact object, saves to backend database and returns a list of ids for the saved Contact object. Id is generated using UUID
  * `void` update(Contact)
    * This method takes a ``Contact`` object with its ``Phone`` object and updates the database entry if id of the ``Contact`` exists in database, otherwise throws an ``IllegalArgumentException``
  * `void` delete(Contact)
    * This method takes a ``Contact`` object with its ``Phone`` object and delete the database entry if id of the ``Contact`` exists in database, otherwise throws an ``IllegalArgumentException``
  * `List<Contact>` GetContacts()
    * This method returns a list of all contacts in the current database.
  * `List<Phone>` GetPhones()
    * This method returns a list of all phones in the current database.

## Operation Example
* Save
  * Input
    * `inputContact = {"123", "John", "Doe", [Phone = {"1t3", "123456789", WORK, "", false}]}`
  * Operation
    * ContactDao.save(inputContact)
  * Output
    * "123"
* Update
  * Input
    * `inputContact = {"123", "John", "Doe", [Phone = {"1t3", "123456789", WORK, "", false}]}`
"README.md" 55L, 2148C

