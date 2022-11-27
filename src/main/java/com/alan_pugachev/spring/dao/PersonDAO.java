package com.alan_pugachev.spring.dao;

import com.alan_pugachev.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static final String URL = "jdbc:postgesql://localhost:5432/spring";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "666";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //Database access methods
    public List<Person> index() throws SQLException {
        List<Person> personList = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM person";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setUsername(resultSet.getString("username"));
            person.setFirstName(resultSet.getString("firstname"));
            person.setSecondName(resultSet.getString("secondname"));
            person.setEmail(resultSet.getString("email"));

            personList.add(person);
        }

        return personList;
    }

}
