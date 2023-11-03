package com.example.ishop.db;

import com.example.ishop.exceptions.CustomerAlreadyExistException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T get(T t);

    Connection getConnection();

    List<T> getAll();

    List<T> getAll(String field, String order);

    void saveAll(List<T> t);

    void save(T t) throws CustomerAlreadyExistException, SQLException;

    void update(T t, String[] params);

    void delete(T t);

}
