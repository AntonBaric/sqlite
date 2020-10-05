package com.company;

import java.sql.*;

public class Database {
    Connection conn = null;
    Database() {
        System.out.println("Hello from the database");

        connectToDatabase();

        // postNewPerson("Emma", 21);

         deletePerson(6);

        // updatePerson(3, "Elsa", 20);

        getAllPersons();

       // getAllPersonsOlderThan(30);
    }

    public void updatePerson(int id, String name, int age) {
        // "UPDATE persons SET name = ?, age = ? WHERE id = ?"

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE persons SET name = ?, age = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePerson(int id) {
        //"DELETE FROM persons WHERE persons.id = ?"

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM persons WHERE persons.id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void postNewPerson(String name, int age) {
        //"INSERT INTO persons(name, age) VALUES ("Name", 20)"

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO persons (name, age) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setInt(2, age);

            statement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllPersons() {

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM persons");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int personID = resultSet.getInt("id");
                String personName = resultSet.getString("name");
                int personAge = resultSet.getInt("age");

                System.out.printf("ID: %d, name: %s, age: %d \n", personID, personName, personAge);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllPersonsOlderThan(int age) {
        // "SELECT * FROM persons WHERE persons.age > 15"

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM persons WHERE persons.age > ?");
            statement.setString(1, "Loke");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int personID = resultSet.getInt("id");
                String personName = resultSet.getString("name");
                int personAge = resultSet.getInt("age");

                System.out.printf("ID: %d, name: %s, age: %d \n", personID, personName, personAge);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:stimdb.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
