package com.example.ishop.db.dbimplementation;

import com.example.ishop.db.Dao;
import com.example.ishop.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static com.example.ishop.db.DBConstants.DBPATH;

public class CustomersSQLite implements Dao<Customer> {
    Properties queries;

    public CustomersSQLite() {
        this.queries = new Properties();
        try {
            queries.load(Files.newInputStream(Paths.get("src/main/resources/com/example/ishop/queries.properties")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Customer get(Customer customer) {
        if (isCustomerExist(customer)) {
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("getCustomer"));
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new Customer(id, username, password, email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DBPATH);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public List<Customer> getAll(String field, String order) {
        return null;
    }

    @Override
    public void saveAll(List<Customer> t) {

    }

    @Override
    public void save(Customer customer) throws SQLException {
        if (!isCustomerExist(customer)) {
            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("createCustomer"));
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.setString(3, customer.getEmail());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else {
            throw new SQLException();
        }
    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void delete(Customer customer) {
    }

    private boolean isCustomerExist(Customer customer) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("checkExistCustomer"));
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = resultSet.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
