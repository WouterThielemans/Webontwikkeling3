package domain.db;

import domain.model.DomainException;
import domain.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonDB {
    private Properties properties;
    String url;

    public PersonDBSQL(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void addPerson(Person person) {
        if (person == null) throw new DomainException("Nothing to add.");
        String sql = "INSERT INTO person(userid,email,password,firstname,lastname) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,person.getUserid());
            statement.setString(2,person.getEmail());
            statement.setString(3,person.getPassword());
            statement.setString(4,person.getFirstName());
            statement.setString(5,person.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(String personId) {
        if (personId == null) throw new DomainException("Nothing to get.");
        String sql = "DELETE FROM person WHERE userid=?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,personId);
            statement.execute();
            } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public int getNumberOfPersons() {
        return 0;
    }

    @Override
    public Person getPerson(String personId) {
        Person person = new Person();
        if (personId == null) throw new DomainException("Nothing to get.");
        String sql = "SELECT * FROM person WHERE userid=?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,personId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");

                person = new Person(userid, email,password, firstName, lastName);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personen = new ArrayList<Person>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM person");

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");

                Person person = new Person(userid, email, password, firstName, lastName);
                personen.add(person);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return personen;
    }
}
