# Hibernate Contact Management

Contact Management System created to store a contact's phone number as in the directory system.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Project Object Overview Diagram
![alt text](https://github.com/wangborui/Hibernate/blob/master/Relationships.png)

### Prerequisites
* Java Programming
* SQL Database Knowledge

* [Intellij](https://www.jetbrains.com/idea/download/#section=mac) - IDEA
* [Hibernate](http://hibernate.org/orm/releases/) - ORM
* [Latest JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Hibernate Youtube Tutorial](https://www.youtube.com/watch?v=rk2zcyzeK3U)

### Installing
```
1. mkdir <directory>
2. git clone https://github.com/wangborui/Hibernate.git
3. Intellij import project
```

## Running the tests

In Intellij, run all tests under Hibernate/src/test/java/com.contact.application.controller.contact_management/ folder

### Break down into end to end tests

* ContactDaoTest.java
```
Testing DAO object for save, update, and delete operations from a high level. Ensuring the given input produces certain outputs. Mokito is used in the unit test to restrict testing to only DAO method and no other objects.
```

* ContactTest.java
```
Test the Contact object to see if it can be correctly saved, updated, and deleted. This unit test involves H2 database operations
```

* TestUtils.java
```
Helper class for unit testing.
```

### And coding style tests

To be added

## Deployment

To be added

## Built With

* [Hibernate](http://hibernate.org/orm/releases/) - ORM
* [Latest JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://github.com/wangborui/Hibernate/blob/master/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use Git for versioning.

## Authors

* **Borui Wang** - *Initial work* - [Github Repo](https://github.com/wangborui)

See also the list of [contributors](https://github.com/wangborui/Hibernate/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Big shoutout to **[Brady Vitrano](https://github.com/brady-vitrano)** for mentoring and code reviews

