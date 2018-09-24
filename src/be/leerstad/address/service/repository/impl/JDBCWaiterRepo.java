package be.leerstad.address.service.repository.impl;

import be.leerstad.address.business.Person;
import be.leerstad.address.service.repository.IPersonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCWaiterRepo implements IPersonRepository {
    private static String connURL = "jdbc:mysql://172.16.1.11/HERMAN"; // jdbc:mysql://<host>/<dBaseName>
    private static String user = "HERMAN";
    private static String password = "JEROEN";


    @Override
    public List<Person> getAll() {
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from waiters");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Person> result = new ArrayList<>();
            while (resultSet.next()) {
                Person p = new Person(resultSet.getInt("waiterID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
                result.add(p);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean add(Person aggregate) {
        Boolean result = false;

        if (!(contains(aggregate))) {
            try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `waiters`(`waiterID`, `lastName`, `firstName`) VALUES (?,?,?)");
                preparedStatement.setInt(1, aggregate.getId());
                preparedStatement.setString(2, aggregate.getLastName());
                preparedStatement.setString(3, aggregate.getFirstName());
                Integer updateRows = preparedStatement.executeUpdate();

                if (updateRows > 0)
                    result = true;


            } catch (SQLException e) {
                e.printStackTrace();
                return result;
            }
        }
        return result;
    }

    public boolean add(Person aggregate, String password) {
        Boolean result = false;

        if (!(contains(aggregate))) {
            try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `waiters`(`waiterID`, `lastName`, `firstName`, `password`) VALUES (?,?,?,?)");
                preparedStatement.setInt(1, aggregate.getId());
                preparedStatement.setString(2, aggregate.getFirstName());
                preparedStatement.setString(3, aggregate.getLastName());
                preparedStatement.setString(4, password);
                Integer updateRows = preparedStatement.executeUpdate();

                if (updateRows > 0)
                    result = true;


            } catch (SQLException e) {
                e.printStackTrace();
                return result;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Person aggregate) {
        Boolean result = false;
        if (aggregate == null)
            return false;
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `persons` WHERE `id` = ?");
            preparedStatement.setInt(1, aggregate.getId());
            Integer updateRows = preparedStatement.executeUpdate();

            if (updateRows > 0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public Person getByID(int id) {
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `id`, `country`, `name` FROM `persons` WHERE `id` = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Person p = new Person();
            while (resultSet.next()) {
                p = new Person(resultSet.getInt("waiterID"), resultSet.getString("firstname"), resultSet.getString("lastname"));
            }
            return p;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Boolean contains(Person person) {
        if (person == null)
            return false;
        Person foundPerson = this.getByID(person.getId());

        if (foundPerson.getId() == null) {
            return false;
        } else return true;
    }
}
