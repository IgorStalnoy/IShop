package com.example.ishop.db.dbimplementation;

import com.example.ishop.db.Dao;
import com.example.ishop.model.Product.Computer;
import com.example.ishop.model.Product.Furniture;
import com.example.ishop.model.Product.HouseholdChemicals;
import com.example.ishop.model.Product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.example.ishop.db.DBConstants.DBPATH;

public class ProductsSQLite implements Dao<Product> {

    Properties queries;

    public ProductsSQLite() {
        this.queries = new Properties();
        try {
            queries.load(Files.newInputStream(Paths.get("src/main/resources/com/example/ishop/queries.properties")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection()) {
            List<Product> productsList = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("getAllProductsFromDB"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("categoryID") == 1) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String brand = resultSet.getString("brand");
                    String model = resultSet.getString("model");
                    String type = resultSet.getString("type");
                    int processorSpeed = resultSet.getInt("processorSpeed");
                    int ramSize = resultSet.getInt("ramSize");
                    int hddSize = resultSet.getInt("hddSize");
                    Computer computer = new Computer(id, categoryID, price, count, brand, model, type, processorSpeed, ramSize, hddSize);
                    productsList.add(computer);
                } else if (resultSet.getInt("categoryID") == 2) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String model = resultSet.getString("model");
                    String material = resultSet.getString("material");
                    String color = resultSet.getString("color");
                    Furniture furniture = new Furniture(id, categoryID, price, count, model, material, color);
                    productsList.add(furniture);
                } else if (resultSet.getInt("categoryID") == 3) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String model = resultSet.getString("model");
                    String brand = resultSet.getString("brand");
                    String appointment = resultSet.getString("appointment");
                    String smell = resultSet.getString("smell");
                    HouseholdChemicals householdChemicals = new HouseholdChemicals(id, categoryID, price, count, model, brand,appointment,smell);
                    productsList.add(householdChemicals);
                }
            }
            return productsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> getAll(String field, String order) {
        try (Connection connection = getConnection()) {
            List<Product> productsList = new ArrayList<>();
            String query = queries.getProperty("getAllProductsFromDB");
            query = query + " ORDER BY " + field + " " + order;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("categoryID") == 1) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String brand = resultSet.getString("brand");
                    String model = resultSet.getString("model");
                    String type = resultSet.getString("type");
                    int processorSpeed = resultSet.getInt("processorSpeed");
                    int ramSize = resultSet.getInt("ramSize");
                    int hddSize = resultSet.getInt("hddSize");
                    Computer computer = new Computer(id, categoryID, price, count, brand, model, type, processorSpeed, ramSize, hddSize);
                    productsList.add(computer);
                } else if (resultSet.getInt("categoryID") == 2) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String model = resultSet.getString("model");
                    String material = resultSet.getString("material");
                    String color = resultSet.getString("color");
                    Furniture furniture = new Furniture(id, categoryID, price, count, model, material, color);
                    productsList.add(furniture);
                } else if (resultSet.getInt("categoryID") == 3) {
                    int id = resultSet.getInt("id");
                    int categoryID = resultSet.getInt("categoryID");
                    int price = resultSet.getInt("price");
                    int count = resultSet.getInt("count");
                    String model = resultSet.getString("model");
                    String brand = resultSet.getString("brand");
                    String appointment = resultSet.getString("appointment");
                    String smell = resultSet.getString("smell");
                    HouseholdChemicals householdChemicals = new HouseholdChemicals(id, categoryID, price, count, model, brand,appointment,smell);
                    productsList.add(householdChemicals);
                }
            }
            return productsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void saveAll(List<Product> t) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("saveProductsToDB"));
            for (Product i : t) {
                if (i instanceof Computer) {
                    preparedStatement.setInt(1, i.getCategoryId());
                    preparedStatement.setInt(2, i.getPrice());
                    preparedStatement.setInt(3, i.getCount());
                    preparedStatement.setString(4, i.getModel());
                    preparedStatement.setString(5, ((Computer) i).getBrand());
                    preparedStatement.setString(6, ((Computer) i).getType());
                    preparedStatement.setInt(7, ((Computer) i).getProcessorSpeed());
                    preparedStatement.setInt(8, ((Computer) i).getRamSize());
                    preparedStatement.setInt(9, ((Computer) i).getHddSize());
                    preparedStatement.execute();
                } else if (i instanceof Furniture) {
                    preparedStatement.setInt(1, i.getCategoryId());
                    preparedStatement.setInt(2, i.getPrice());
                    preparedStatement.setInt(3, i.getCount());
                    preparedStatement.setString(4, i.getModel());
                    preparedStatement.setString(10, ((Furniture) i).getMaterial());
                    preparedStatement.setString(11, ((Furniture) i).getColor());
                    preparedStatement.execute();
                } else if (i instanceof HouseholdChemicals) {
                    preparedStatement.setInt(1, i.getCategoryId());
                    preparedStatement.setInt(2, i.getPrice());
                    preparedStatement.setInt(3, i.getCount());
                    preparedStatement.setString(4, i.getModel());
                    preparedStatement.setString(5, ((HouseholdChemicals) i).getBrand());
                    preparedStatement.setString(12, ((HouseholdChemicals) i).getAppointment());
                    preparedStatement.setString(13, ((HouseholdChemicals) i).getSmell());
                    preparedStatement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public Product get(Product product) {
        return null;
    }

    @Override
    public void save(Product product) {

    }
    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }

}
