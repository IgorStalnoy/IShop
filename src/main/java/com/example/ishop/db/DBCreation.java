package com.example.ishop.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.ishop.db.DBConstants.DBNAME;
import static com.example.ishop.db.DBConstants.DBPATH;

public class DBCreation {

    private Connection connection;

    public boolean isDatabaseExist() {
        File file = new File("src/main/java/com/example/ishop/db/" + DBNAME);
        return file.exists();

    }

    public void createDatabase() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(DBPATH);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE CUSTOMERS (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT, email TEXT UNIQUE)");
            statement.execute("CREATE TABLE PRODUCTS (id INTEGER PRIMARY KEY AUTOINCREMENT, categoryID INTEGER, price INTEGER," +
                    " count INTEGER, model TEXT,brand TEXT, type TEXT, processorSpeed INTEGER, ramSize INTEGER, hddSize INTEGER, material TEXT, color TEXT, appointment TEXT, smell TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
