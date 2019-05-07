package domain.db;

import domain.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDB {
    Person getPerson(String personId) throws SQLException;

    List<Person> getAllPersons();

    void addPerson(Person person) throws SQLException;

    void updatePerson(Person person) throws SQLException;

    void deletePerson(String personId) throws SQLException;

    int getNumberOfPersons();

}
